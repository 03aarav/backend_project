package com.example.demo.Service;

import com.example.demo.Model.RefreshToken;

import java.util.Optional;

public interface RefreshTokenService {
    RefreshToken createRefreshToken(String userId);
    Optional<RefreshToken> findByToken(String token);
    void deleteByUserId(String userId);
}

