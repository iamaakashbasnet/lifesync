package com.example.lifesync.token;

public interface TokenInvalidate {
    void invalidateToken(String token);
    boolean isValid(String token);
}