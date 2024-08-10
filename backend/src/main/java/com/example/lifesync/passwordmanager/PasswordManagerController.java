package com.example.lifesync.passwordmanager;

import com.example.lifesync.token.TokenService;
import com.example.lifesync.user.User;
import com.example.lifesync.user.UserService;
import com.example.lifesync.utils.UtilFunctions;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.crypto.SecretKey;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/passwordmangager")
public class PasswordManagerController {

    private final UtilFunctions utilFunctions;

    private final TokenService tokenService;

    private final UserService userService;

    private final HttpServletRequest httpServletRequest;

    private final PasswordManagerRepository passwordManagerRepository;

    private final PasswordEncoder passwordEncoder;
    private final PasswordManagerService passwordManagerService;

    @GetMapping("/allpasswords")
    public ResponseEntity<List<PasswordManager>> getPasswords() {
        return null;
    }

    @GetMapping("/password")
    public ResponseEntity<List<PasswordManager>> getPassword() {
        // list all encrypted password
        List<PasswordManager> passwordList = passwordManagerRepository.findAll();
        return null;
    }

    @PostMapping("/addpassword")
    public ResponseEntity<PasswordManager> createPassword(@RequestBody PasswordManager passwordManager) {
        return null;
    }

    @PostMapping("/verifyPassword")
    public ResponseEntity<List<PasswordResponseDTO>> verifyPassword(@RequestBody PasswordRequestDTO requestDTO) throws Exception {
        String token = utilFunctions.extractTokenFromRequest(httpServletRequest);
        String username = tokenService.extractUsername(token);
        User user = userService.findByUsername(username);

        return null;
   }

}
