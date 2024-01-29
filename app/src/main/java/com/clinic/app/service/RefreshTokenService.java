package com.clinic.app.service;

import com.clinic.app.model.RefreshToken;
import com.clinic.app.model.UserApp;
import com.clinic.app.repository.RefreshTokenRepository;
import com.clinic.app.repository.UserAppRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

@Service
public class RefreshTokenService {
    private final RefreshTokenRepository refreshTokenRepository;
    private final UserAppRepository userAppRepository;

    public RefreshTokenService(RefreshTokenRepository refreshTokenRepository, UserAppRepository userAppRepository) {
        this.refreshTokenRepository = refreshTokenRepository;
        this.userAppRepository = userAppRepository;
    }

    public Optional<RefreshToken> findByToken(String token) {
        return refreshTokenRepository.findByToken(token);
    }

    public RefreshToken createRefreshToken(long userId) {
        Optional<UserApp> user = userAppRepository.findById(userId);
        if (user.isPresent()) {
            RefreshToken refreshToken = RefreshToken.builder()
                    .user(user.get())
                    .expiryDate(new Date().getTime() + 2 * 24 * 60 * 60 * 1000)
                    .token(String.valueOf(new Random().nextLong()))
                    .build();
            refreshTokenRepository.save(refreshToken);
            return refreshToken;
        } else {
            return null;
        }
    }

    public RefreshToken verifyExpiration(RefreshToken token) throws Exception {
        if (token.getExpiryDate().compareTo(new Date().getTime()) < 0) {
            refreshTokenRepository.delete(token);
            throw new Exception("Refresh token expired");
        } else {
            return token;
        }
    }

    @Transactional
    public void deleteByUserId(Long userId) {
        Optional<UserApp> user = userAppRepository.findById(userId);
        user.ifPresent(refreshTokenRepository::deleteByUser);
    }
}
