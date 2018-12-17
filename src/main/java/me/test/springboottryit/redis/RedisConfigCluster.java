package me.test.springboottryit.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisNode;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.HashSet;
import java.util.Set;

/**
 * @author paranoidq
 * @since 1.0.0
 */
@Configuration
@PropertySource("${redis.config}")
@ConditionalOnProperty(name = "redis.mode", havingValue = "cluster")
public class RedisConfigCluster {

    @Autowired
    RedisConfig redisCommons;

    @Value("${redis.password}")
    private String password;

    @Value("${redis.cluster.nodes}")
    private String clusterNodes;

    @Value("${redis.cluster.max-redirects}")
    private Integer maxRedirects;

    /**
     * Redis集群的配置
     *
     * @return RedisClusterConfiguration
     * @throws
     * @autor lpl
     * @date 2017年12月22日
     */
    @Bean
    public RedisClusterConfiguration redisClusterConfiguration() {
        RedisClusterConfiguration redisClusterConfiguration = new RedisClusterConfiguration();
        //Set<RedisNode> clusterNodes
        String[] serverArray = clusterNodes.split(",");

        Set<RedisNode> nodes = new HashSet<RedisNode>();

        for (String ipPort : serverArray) {
            String[] ipAndPort = ipPort.split(":");
            nodes.add(new RedisNode(ipAndPort[0].trim(), Integer.valueOf(ipAndPort[1])));
        }

        redisClusterConfiguration.setClusterNodes(nodes);
        redisClusterConfiguration.setMaxRedirects(maxRedirects);
        redisClusterConfiguration.setPassword(password);

        return redisClusterConfiguration;
    }

    /**
     * RedisCluster配置
     *
     * @param redisClusterConfiguration
     * @param jedisClientConfiguration
     * @return
     */
    @Bean
    public JedisConnectionFactory JedisConnectionFactory(RedisClusterConfiguration redisClusterConfiguration, JedisClientConfiguration jedisClientConfiguration) {
        JedisConnectionFactory JedisConnectionFactory =
            new JedisConnectionFactory(redisClusterConfiguration, jedisClientConfiguration);
        return JedisConnectionFactory;
    }

    /**
     * 实例化 RedisTemplate 对象
     *
     * @return
     */
    @Bean
    public RedisTemplate<String, Object> functionDomainRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisCommons.initDomainRedisTemplate(redisTemplate, redisConnectionFactory);
        return redisTemplate;
    }
}
