package com.example.lifesync.email;

import com.example.lifesync.rabbitmq.RabbitMQProducer;
import com.example.lifesync.token.TokenService;
import com.example.lifesync.user.User;
import com.example.lifesync.user.UserService;
import com.example.lifesync.utils.UtilFunctions;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/email")
@RequiredArgsConstructor
public class EmailController {

    private final UserService userService;

    private final UtilFunctions utilFunctions;

    private final HttpServletRequest request;

    private final TokenService tokenService;

    private final EmailTokenService emailTokenService;

    private final RabbitMQProducer rabbitMQProducer;

    @GetMapping("/send")
    @Transactional
    public void sendEmail() {
        String token = utilFunctions.extractTokenFromRequest(request);
        String username = tokenService.extractUsername(token);
        User user = userService.findByUsername(username);

        EmailToken emailToken = EmailToken.builder()
                .token(emailTokenService.generateToken())
                .user(user)
                .build();

        emailTokenService.save(emailToken);

        EmailSendDTO emailSendDTO = EmailSendDTO.builder()
                .email(user.getEmail())
                .token(emailToken.getToken())
                .build();

        rabbitMQProducer.send(emailSendDTO);
    }

    @PostMapping("/verify")
    @Transactional
    public ResponseEntity<String> verifyEmail(@RequestBody EmailTokenReceiveDTO emailToken) {
        String access_token = utilFunctions.extractTokenFromRequest(request);
        String username = tokenService.extractUsername(access_token);
        User user = userService.findByUsername(username);

        if (emailTokenService.validateToken(emailToken.getToken(), user)) {
            return ResponseEntity.ok("Email verified");
        } else {
            throw new BadCredentialsException("Token is invalid");
        }
    }

}
