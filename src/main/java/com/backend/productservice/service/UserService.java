package com.backend.productservice.service;

import com.backend.productservice.model.Token;
import com.backend.productservice.repository.TokenRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.backend.productservice.model.User;
import com.backend.productservice.repository.UserRepository;

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

private final TokenRepository tokenRepository;
    public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder, TokenRepository tokenRepository) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.tokenRepository = tokenRepository;
    }

    public User signUp(String fullname, String email, String password) {
        User user = User.builder()
        .fullname(fullname)
        .email(email)
        .hashedPassword(bCryptPasswordEncoder.encode(password))
        .build();

        return userRepository.save(user);
    }

    public Token login(String email, String password) {

        User user = userRepository.findByEmail(email).orElse(null);
        if(user == null)
            return null;
        if(bCryptPasswordEncoder.matches(password, user.getHashedPassword())){
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DAY_OF_MONTH, 30);
            Date expiryDate = calendar.getTime();
            Token token = Token.builder()
                    .user(user)
                    .expiryDate(expiryDate)
                    .value(RandomStringUtils.random(30, true, true))
            .build();
           return tokenRepository.save(token);
        }else {
            return null;
        }


    }

    public void logout(String token) {
        Optional<Token> token1 = tokenRepository.findByValueAndExpiryDateGreaterThan(token, new java.sql.Date(System.currentTimeMillis()));
        if(token1.isPresent()){
            tokenRepository.deleteById(token1.get().getId());
        }else {
            throw new IllegalArgumentException("Invalid token");
        }
    }

    public User validateToken(String token) {
        Optional<Token> token1 = tokenRepository.findByValueAndExpiryDateGreaterThan(token, new java.sql.Date(System.currentTimeMillis()));
        if(token1.isPresent()){
                return token1.get().getUser();
        }else {
            throw new IllegalArgumentException("Invalid/expired token");
        }
    }
}
