package com.example.lifesync.email;

import com.example.lifesync.user.User;
import com.rabbitmq.client.AMQP;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

@Service
@RequiredArgsConstructor
public class EmailTokenService {

    private final EmailTokenRepository emailTokenRepository;

    private static final SecureRandom secureRandom = new SecureRandom();

    public Integer generateToken() {
        return secureRandom.nextInt(900000) + 100000;
    }

    public Boolean validateToken(Integer token, User user) {
        EmailToken emailToken = emailTokenRepository.findTokenByUser(user);
        return emailToken.getToken().equals(token);
    }

    public EmailToken getTokenByUser(User user) {
        return emailTokenRepository.findTokenByUser(user);
    }

    public EmailToken save(EmailToken emailToken) {
        emailTokenRepository.save(emailToken);
        return emailToken;
    }

}
