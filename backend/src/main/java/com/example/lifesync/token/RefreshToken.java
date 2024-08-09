package com.example.lifesync.token;


import com.example.lifesync.user.User;
import jakarta.annotation.security.DenyAll;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RefreshToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String token;
    private Instant expiresAt;
    private Boolean valid;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

}
