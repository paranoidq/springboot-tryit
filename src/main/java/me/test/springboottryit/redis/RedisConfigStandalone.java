package me.test.springboottryit.redis;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;


/**
 * @author paranoidq
 * @since 1.0.0
 */
@Configuration
@PropertySource("${redis.config}")
@ConditionalOnProperty(name = "redis.mode", havingValue = "standalone")
public class RedisConfigStandalone {

    @Value("${redis.hostName}")
    private String hostName;

    @Value("${redis.port}")
    private int port;

    @Value("${redis.password}")
    private String password;

    @Value("${redis.connectTimeout}")
    private long connectTimeout;

    @Value("${redis.readTimeout}")
    private long readTimeout;


    @Bean
    RedisStandaloneConfiguration redisStandaloneConfiguration() {
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
        redisStandaloneConfiguration.setDatabase(0);
        redisStandaloneConfiguration.setHostName(hostName);
        redisStandaloneConfiguration.setPassword(password);
        redisStandaloneConfiguration.setPort(port);
        return redisStandaloneConfiguration;
    }

    /**
     * Redis单机配置
     *
     * @param redisStandaloneConfiguration
     * @param jedisClientConfiguration
     * @return
     */
    @Bean
    public JedisConnectionFactory redisConnectionFactory(RedisStandaloneConfiguration redisStandaloneConfiguration, JedisClientConfiguration jedisClientConfiguration) {
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory(
            redisStandaloneConfiguration, jedisClientConfiguration
        );
        return jedisConnectionFactory;
    }

    @Bean
    public RedisTemplate<String, Object> functionDomainRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        RedisConfig.initDomainRedisTemplate(redisTemplate, redisConnectionFactory);
        return redisTemplate;
    }
}
