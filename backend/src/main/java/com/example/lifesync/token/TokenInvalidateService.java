package com.example.lifesync.token;

import com.example.lifesync.user.User;
import com.example.lifesync.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TokenInvalidateService implements TokenInvalidate {

    private final TokenService tokenService;

    private final UserService userService;


    @Autowired
    private RefreshTokenService refreshTokenService;

    @Override
    public boolean isValid(String token) {
        String username = tokenService.extractUsername(token);
        User user = userService.findByUsername(username);
        RefreshToken refreshToken = refreshTokenService.getRefreshTokenByUser(user);
        System.out.println(refreshToken.getValid());
        return refreshToken.getValid();
    }

    @Override
    public void invalidateToken(String token) {
        String username = tokenService.extractUsername(token);
        User user = userService.findByUsername(username);
        RefreshToken refreshToken = refreshTokenService.getRefreshTokenByUser(user);
        refreshToken.setValid(false);
        refreshTokenService.save(refreshToken);
    }

}
