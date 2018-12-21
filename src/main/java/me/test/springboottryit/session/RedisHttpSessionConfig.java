package me.test.springboottryit.session;

import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * @author paranoidq
 * @since 1.0.0
 */
@Configuration
@EnableRedisHttpSession
public class RedisHttpSessionConfig {
}
