package com.backend.productservice.repository;

import com.backend.productservice.model.Token;
import com.backend.productservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<Token, Long>{

    Token save(Token token);

    Optional<Token> findByValueAndExpiryDateGreaterThan(String value,Date date);

}