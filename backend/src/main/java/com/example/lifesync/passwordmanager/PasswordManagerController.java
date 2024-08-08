package com.example.lifesync.passwordmanager;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/passwordmangager")
public class PasswordManagerController {

    @GetMapping("/allpasswords")
    public ResponseEntity<List<PasswordManager>> getPasswords() {
        return null;
    }

    @GetMapping("/password")
    public ResponseEntity<PasswordManager> getPassword() {
        return null;
    }

    @PostMapping("/addpassword")
    public ResponseEntity<PasswordManager> createPassword(@RequestBody PasswordManager passwordManager) {
        return null;
    }

}
