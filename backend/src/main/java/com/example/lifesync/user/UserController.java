package com.example.lifesync.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @GetMapping("/ping")
    public String test() {
        try {
            return "Welcome";
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Autowired
    private UserService userService;

    @Autowired
    private UserRoleRepository authorityService;

    @GetMapping("/users/{name}")
    public ResponseEntity<User> users(@PathVariable String name) {
        User user = userService.findByUsername(name);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/users")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        System.out.println(user.getPassword());
        userService.save(user);
        return ResponseEntity.ok(user);
    }


}

