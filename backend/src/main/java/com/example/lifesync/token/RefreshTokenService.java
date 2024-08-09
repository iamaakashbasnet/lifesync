package com.example.lifesync.token;

import com.example.lifesync.user.User;
import com.example.lifesync.user.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;

@Service
@Transactional
public class RefreshTokenService {

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private TokenService tokenService;

    public Boolean verifyUserExist(String token) {
        Optional<RefreshToken> refreshToken = refreshTokenRepository.findByToken(token);
        Optional<Boolean> b = refreshToken.map(refreshtoken -> {
            return refreshtoken.getUser() != null;
        });
        return false;
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
                .expiresAt(Instant.now().plusMillis(600000))
                .build();
        return refreshTokenRepository.save(refreshToken);
    }

    public Optional<RefreshToken> findByToken(String token) {
        return refreshTokenRepository.findByToken(token);
    }

    public RefreshToken verifyExpiration(RefreshToken token) {
        if (token.getExpiresAt().isBefore(Instant.now())) {
            refreshTokenRepository.delete(token);
            throw new RuntimeException(token.getToken() + "Refresh token is expired");
        }
        return token;
    }

}
