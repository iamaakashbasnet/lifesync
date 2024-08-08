package com.example.lifesync.auth;


import com.example.lifesync.token.*;
import com.example.lifesync.user.User;
import com.example.lifesync.user.UserRepository;
import com.example.lifesync.utils.UtilFunctions;
import jakarta.servlet.http.HttpServletRequest;
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
public class AuthController {
    Logger logger = Logger.getLogger(AuthController.class.getName());

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UtilFunctions utilFunctions;

    @Autowired
    private TokenService jwtService;

    @Autowired
    private RefreshTokenService refreshTokenService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenInvalidateService tokenInvalidateService;

    @PostMapping("/api/v1/token/login")
    public JwtResponseDTO AuthenticateAndGetToken(@RequestBody AuthRequestDTO authRequestDTO){

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequestDTO.getUsername(), authRequestDTO.getPassword()));

        if(authentication.isAuthenticated()){

            User user = userRepository.findByUsername(authentication.getName());

            RefreshToken refreshToken = refreshTokenService.getRefreshTokenByUser(user);
            if (refreshToken != null) {
                return JwtResponseDTO.builder()
                        .accessToken(jwtService.GenerateToken(authRequestDTO.getUsername()))
                        .refreshToken(refreshToken.getToken())
                        .build();
            }

            RefreshToken refreshTokenTwo = refreshTokenService.createRefreshToken(authRequestDTO.getUsername());

            return JwtResponseDTO.builder()
                    .accessToken(jwtService.GenerateToken(authRequestDTO.getUsername()))
                    .refreshToken(refreshTokenTwo.getToken())
                    .build();
        } else {
            throw new UsernameNotFoundException("invalid user request..!!");
        }
    }

    @PostMapping("/api/v1/token/refresh")
    public JwtResponseDTO RefreshAndGetToken(@RequestBody RefreshTokenRequestDTO refreshTokenDTO) {
        Optional<RefreshToken> refreshToken = refreshTokenService.findByToken(refreshTokenDTO.getRefreshToken());
        return refreshToken
                .map(token -> refreshTokenService.verifyExpiration(token))
                .map(RefreshToken::getUser)
                .map(user -> {
                    String accessToken = jwtService.GenerateToken(user.getUsername());
                    return JwtResponseDTO.builder()
                            .accessToken(accessToken)
                            .refreshToken(refreshTokenDTO.getRefreshToken())
                            .build();
                }).orElseThrow(() -> new RuntimeException("Refresh token not found in db"));
    }

    @GetMapping("/api/v1/token/logout")
    public ResponseEntity<String> Logout(HttpServletRequest request) {
        String token = utilFunctions.extractTokenFromRequest(request);
        tokenInvalidateService.invalidateToken(token);

        return ResponseEntity.ok("Successfully loggedout");
    }

}
