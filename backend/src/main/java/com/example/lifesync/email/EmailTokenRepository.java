package com.example.lifesync.email;

import com.example.lifesync.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailTokenRepository extends JpaRepository<EmailToken, Integer> {

    public EmailToken findTokenByUser(User user);

}
