package com.sirius1b.auth.configs;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.SerializationFeature;
//import org.springframework.context.annotation.Bean;
//import org.springframework.data.redis.connection.RedisConnectionFactory;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
//import org.springframework.data.redis.serializer.StringRedisSerializer;

public class RedisConfig {
//
//    @Bean
//    public <T> RedisTemplate<String, > redisTemplate(RedisConnectionFactory redisConnectionFactory) {
//        RedisTemplate<String, T> template = new RedisTemplate<>();
//        template.setConnectionFactory(redisConnectionFactory);
//
//        Jackson2JsonRedisSerializer<T> serializer = new Jackson2JsonRedisSerializer<>(T.class);
//        ObjectMapper objectMapper = new ObjectMapper();
//        objectMapper.enable(SerializationFeature.INDENT_OUTPUT); // Optional: Pretty print JSON
//
//        serializer.setObjectMapper(objectMapper);
//
//        template.setKeySerializer(new StringRedisSerializer());
//        template.setValueSerializer(serializer);
//
//        return template;
//    }
}
