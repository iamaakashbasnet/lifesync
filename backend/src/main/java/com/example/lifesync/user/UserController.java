package com.example.lifesync.user;

import com.example.lifesync.token.TokenService;
import com.example.lifesync.utils.UtilFunctions;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {

    @GetMapping("/ping")
    public String test() {
        try {
            return "Welcome";
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    private final UserService userService;

    private final UserRoleRepository authorityService;

    private final UtilFunctions utilFunctions;

    private final HttpServletRequest request;

    private final TokenService tokenService;

    @GetMapping("/requestuser")
    public ResponseEntity<User> users() {
        String token = utilFunctions.extractTokenFromRequest(request);
        String username = tokenService.extractUsername(token);
        User user = userService.findByUsername(username);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/users")
    public ResponseEntity<UserResponseDTO> addUser(@RequestBody User user) {
        System.out.println(user.getPassword());
        userService.save(user);
        UserResponseDTO newUser = UserResponseDTO.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .premium(user.getPremium_user())
                .build();

        return ResponseEntity.ok(newUser);
    }


}

