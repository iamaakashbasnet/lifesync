package com.example.lifesync.passwordmanager;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.util.Base64;
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
    private final String ALGORITHM = "AES";

    // Generate a secret key (for demonstration purposes; normally you'd store this securely)
    public SecretKey generateKey() throws Exception {
        KeyGenerator keyGen = KeyGenerator.getInstance(ALGORITHM);
        keyGen.init(256); // AES-256
        return keyGen.generateKey();
    }

    // Encrypt a password
    public String encrypt(String password, SecretKey key) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encryptedBytes = cipher.doFinal(password.getBytes());
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    // Decrypt a password
    public String decrypt(String encryptedPassword, SecretKey key) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedPassword));
        return new String(decryptedBytes);
    }

    public PasswordManager findOne(int id) {
        return passwordManagerRepository.getReferenceById(id);
    }

}
