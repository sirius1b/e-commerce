package com.sirius1b.auth.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class RedisService {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    public void set(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

    public Object get(String key ) {
        return redisTemplate.opsForValue().get(key);
    }

    public void delete(String key) {
        redisTemplate.delete(key);
    }

    public void mSet(Map<String, Object> jsonMap) {
        redisTemplate.opsForValue().multiSet(jsonMap);
    }

    public Map<String, Object> mGet(String... keys) {
        return redisTemplate.opsForValue().multiGet(List.of(keys)).stream().collect(java.util.stream.Collectors.toMap(obj -> keys[java.util.Arrays.asList(keys).indexOf(obj)], obj -> obj ));
    }
}