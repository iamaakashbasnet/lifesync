package com.example.lifesync.token;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TokenInvalidateService implements TokenInvalidate {

    @Autowired
    private RefreshTokenService refreshTokenService;

    @Override
    public boolean isValid(String token) {
        RefreshToken refreshToken = refreshTokenService.getRefreshTokenByToken(token);
        return refreshToken.getValid();
    }

    @Override
    public void invalidateToken(String token) {
        RefreshToken refreshToken = refreshTokenService.getRefreshTokenByToken(token);
        refreshToken.setValid(false);
        refreshTokenService.save(refreshToken);
    }

}
