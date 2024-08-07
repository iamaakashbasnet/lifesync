package com.example.lifesync.user;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.List;


@Configuration
public class DataLoader {

    @Autowired
    private UserRoleRepository authorityRepository;

    @Autowired
    private UserRepository userRepository;

/*    @PostConstruct
    public void dbInit() {
        UserRole authorityUser = new UserRole("ROLE_USER");
        UserRole authorityAdmin = new UserRole("ROLE_ADMIN");

        authorityRepository.save(authorityUser);
        authorityRepository.save(authorityAdmin);

        User adminuser = new User("admin", "admin", List.of(authorityAdmin));
        userRepository.save(adminuser);
    }*/
}


