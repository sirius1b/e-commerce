package com.sirius1b.auth.repos;

import com.sirius1b.auth.models.Token;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Long> {

    public Optional<Token> findByValue(String value);
}
