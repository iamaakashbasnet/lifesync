package com.example.lifesync.auth;


import com.example.lifesync.token.*;
import com.example.lifesync.user.User;
import com.example.lifesync.user.UserRepository;
import com.example.lifesync.user.UserService;
import com.example.lifesync.utils.UtilFunctions;
import io.jsonwebtoken.Jwt;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.logging.Logger;

@RestController
@RequiredArgsConstructor
public class AuthController {
    Logger logger = Logger.getLogger(AuthController.class.getName());

    private final TokenService tokenService;
    private final UserService userService;

    private final AuthenticationManager authenticationManager;

    private final UtilFunctions utilFunctions;

    private final TokenService jwtService;

    private final RefreshTokenService refreshTokenService;

    private final UserRepository userRepository;

    private final TokenInvalidateService tokenInvalidateService;

    private final HttpServletResponse response;

    @PostMapping("/api/v1/token/login")
    public ResponseEntity<JwtResponseDTO> AuthenticateAndGetToken(@RequestBody AuthRequestDTO authRequestDTO){

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequestDTO.getUsername(), authRequestDTO.getPassword()));

        if(authentication.isAuthenticated()){

            User user = userRepository.findByUsername(authentication.getName());

            RefreshToken refreshToken = refreshTokenService.getRefreshTokenByUser(user);
            if (refreshToken != null) {
                if (!refreshToken.getValid()) {
                    refreshTokenService.deteteRefreshToken(refreshToken);
                    refreshToken = refreshTokenService.createRefreshToken(authRequestDTO.getUsername());
                }
            } else {
                refreshToken = refreshTokenService.createRefreshToken(authRequestDTO.getUsername());
            }

            // Set the refresh token in a cookie
            Cookie refreshTokenCookie = new Cookie("refreshToken", refreshToken.getToken());
            refreshTokenCookie.setHttpOnly(true);
            refreshTokenCookie.setSecure(true); // Use true in production
            refreshTokenCookie.setPath("/");
            refreshTokenCookie.setMaxAge(7 * 24 * 60 * 60); // Set cookie expiration to 7 days
            response.addCookie(refreshTokenCookie);

            // Generate and return the JWT access token
            JwtResponseDTO responseDTO = JwtResponseDTO.builder()
                    .accessToken(jwtService.GenerateToken(authRequestDTO.getUsername()))
                    .build();

            return ResponseEntity.ok(responseDTO);
        } else {
            throw new UsernameNotFoundException("invalid user request..!!");
        }
    }

    @PostMapping("/api/v1/token/refresh")
    public JwtResponseDTO RefreshAndGetToken(HttpServletRequest request) {
        // Optional<RefreshToken> refreshToken = refreshTokenService.findByToken(refreshTokenDTO.getRefreshToken());
        String refreshTokenFromCookie = refreshTokenService.extractTokenFromCookie(request);

        Optional<RefreshToken> refreshToken = refreshTokenService.findByToken(refreshTokenFromCookie);

        return refreshToken
                .map(refreshTokenService::verifyExpiration)
                .map(RefreshToken::getUser)
                .map(user -> {
                    String accessToken = jwtService.GenerateToken(user.getUsername());
                    return JwtResponseDTO.builder()
                            .accessToken(accessToken)
                            .build();
                }).orElseThrow(() -> new RuntimeException("Refresh token not found in db"));
    }

    @GetMapping("/api/v1/token/logout")
    public ResponseEntity<String> Logout(HttpServletRequest request) {
        String token = utilFunctions.extractTokenFromRequest(request);
        tokenInvalidateService.invalidateToken(token);

        return ResponseEntity.ok("Successfully logged out");
    }

}
