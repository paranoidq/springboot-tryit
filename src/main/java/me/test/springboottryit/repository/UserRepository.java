package me.test.springboottryit.repository;

import me.test.springboottryit.domain.User;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * @author paranoidq
 * @since 1.0.0
 */
// 在引入redis-starter或其他cache-starter的情况下，会自动查找cacheManager
// 如果不想使用，则需要显式指定cacheManager
// e.g. redis-cache需要指定序列化方式
@CacheConfig(cacheNames = "users", cacheManager = "ehcacheCacheManager")
public interface UserRepository extends JpaRepository<User, Long> {

    @Cacheable
    User findByName(String name);

    @Cacheable
    @Query("from User u where u.name=:name")
    User findUser(@Param("name") String name);
}
