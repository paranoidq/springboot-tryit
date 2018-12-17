package me.test.springboottryit.mybatis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author paranoidq
 * @since 1.0.0
 */
@Configuration
@MapperScan(basePackages = "me.test.springboottryit.mybatis.mapper") // 或直接在Mapper类上面添加注解@Mapper
public class MybatisConfig {
}
