package com.tripndrive.shuttle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@SpringBootApplication
public class TndShuttleBackApplication {

    @Configuration
    public static class TndShuttleConfiguration {

        @Autowired
        Environment env;

        @Bean
        public RedisConnectionFactory redisConnectionFactory() {
            JedisConnectionFactory jrcf = new JedisConnectionFactory();
            jrcf.setHostName(env.getProperty("spring.redis.host"));
            jrcf.setPort(env.getProperty("spring.redis.port", Integer.class));
            return jrcf;
        }

        @Bean
        @Autowired
        public RedisTemplate redisTemplate(RedisConnectionFactory redisConnectionFactory) {

            RedisTemplate rt = new RedisTemplate<>();
            rt.setConnectionFactory(redisConnectionFactory);
//            rt.setKeySerializer(new StringRedisSerializer());
//            rt.setValueSerializer(new StringRedisSerializer());
//            rt.setHashKeySerializer(new StringRedisSerializer());
//            rt.setHashValueSerializer(new StringRedisSerializer());

            return rt;
        }

    }

    public static void main(String[] args) {
        SpringApplication.run(TndShuttleBackApplication.class, args);
    }
}
