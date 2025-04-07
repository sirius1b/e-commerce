package com.sirius1b.auth.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.concurrent.TimeUnit;

@Service
public class TokenCacheService {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;


    public void saveToken(String token, long ttl){
        redisTemplate.opsForSet().add(token, token);
        redisTemplate.expireAt(token, Instant.ofEpochSecond(ttl));
    }

    public void deleteToken(String token){
        redisTemplate.delete(token);
    }

    public boolean isMember(String token){
        return Boolean.TRUE.equals(redisTemplate.opsForSet().isMember(token, token));
    }
}
