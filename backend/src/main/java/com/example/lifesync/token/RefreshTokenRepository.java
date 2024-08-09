package com.example.lifesync.token;

import com.example.lifesync.user.User;
import org.springframework.boot.autoconfigure.data.ConditionalOnRepositoryType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

    Optional<RefreshToken> findByToken(String token);

    RefreshToken findRefreshTokenByUser(User user);

}
