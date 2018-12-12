package me.test.springboottryit.cache;

import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.jcache.JCacheCacheManager;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * @author paranoidq
 * @since 1.0.0
 */
@Configuration
public class CacheManagerConfig {

    @Bean("internalCacheManager")
    @Primary
    CacheManager internalCacheManager() {
        return new ConcurrentMapCacheManager();
    }

    @Bean("ehcacheCacheManager")
    CacheManager ehcacheCacheManager() {
        return new EhCacheCacheManager();
    }
}
