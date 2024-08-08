package com.example.lifesync.utils;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class UtilFunctions {
    Logger logger = Logger.getLogger(UtilFunctions.class.getName());

    public String extractTokenFromRequest(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if(authHeader != null && authHeader.startsWith("Bearer ")){
            String token = authHeader.substring(7);
            logger.info("The token is: " + token);
            return token;
        }

        return null;
    }
}
