package me.test.springboottryit.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author paranoidq
 * @since 1.0.0
 */
@Component
@ConfigurationProperties(prefix = "app.properties") // 配置属性解析前缀
@Getter // 只提供getter方法也能注入属性，说明属性注入不是利用的bean的setter方法？
public class AppProperties {

    @Value("${app.properties.test}")
    private String test;    // 引用application.properties中的自定义属性

}
