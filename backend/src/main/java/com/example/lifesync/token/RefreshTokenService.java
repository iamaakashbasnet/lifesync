package com.example.lifesync.token;

import com.example.lifesync.user.User;
import com.example.lifesync.user.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;

    private final UserService userService;

    private final TokenService tokenService;

    private final HttpServletRequest request;

    public Boolean verifyUserExist(String token) {
        Optional<RefreshToken> refreshToken = refreshTokenRepository.findByToken(token);
        Optional<Boolean> b = refreshToken.map(refreshtoken -> {
            return refreshtoken.getUser() != null;
        });
        return false;
    }

    public void deteteRefreshToken(RefreshToken refreshToken) {
        refreshTokenRepository.delete(refreshToken);
    }

    public String extractTokenFromCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("refreshToken".equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        throw new RuntimeException("Refresh token cookie not found");
    }

    public RefreshToken getRefreshTokenByUser(User user) {
        return refreshTokenRepository.findRefreshTokenByUser(user);
    }

    public void save(RefreshToken refreshToken) {
        refreshTokenRepository.saveAndFlush(refreshToken);
    }

    public RefreshToken createRefreshToken(String username) {
        RefreshToken refreshToken = RefreshToken.builder()
                .user(userService.findByUsername(username))
                .token(tokenService.GenerateRefreshToken(username))
                .valid(true)
                .expiresAt(Instant.now().plusMillis(1000 * 60 * 60 * 24 * 7))
                .build();
        return refreshTokenRepository.save(refreshToken);
    }

    public Optional<RefreshToken> findByToken(String token) {
        return refreshTokenRepository.findByToken(token);
    }

    public RefreshToken verifyExpiration(RefreshToken token) {
        if (token.getExpiresAt().isBefore(Instant.now())) {
            refreshTokenRepository.delete(token);
            throw new RuntimeException(token.getToken() + " " + "Refresh token is expired");
        }
        return token;
    }

}
