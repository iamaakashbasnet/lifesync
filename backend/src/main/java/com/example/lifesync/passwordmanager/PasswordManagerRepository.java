package com.example.lifesync.passwordmanager;

import com.example.lifesync.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PasswordManagerRepository extends JpaRepository<PasswordManager, Integer>{

    List<PasswordManager> findPasswordManagerByUser(User user);
}
