package com.example.lifesync.passwordmanager;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PasswordManagerService {

    private final PasswordManagerRepository passwordManagerRepository;

    public void createPasswordManager(PasswordManager password) {
        passwordManagerRepository.save(password);
    }

    public List<PasswordManager> findAll() {
        return passwordManagerRepository.findAll();
    }

    public PasswordManager findOne(int id) {
        return passwordManagerRepository.getReferenceById(id);
    }

}
