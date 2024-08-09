package com.example.lifesync.auth;

import com.example.lifesync.user.User;
import com.example.lifesync.user.UserResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JwtResponseDTO {

    private String accessToken;
    private UserResponseDTO user;

}

