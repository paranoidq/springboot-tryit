package me.test.springboottryit.mvcconfig;


import me.test.springboottryit.interceptor.CustomHandlerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author paranoidq
 * @since 1.0.0
 */
@Configuration
public class CustomWebMvcConfigurer implements WebMvcConfigurer { // 可以存在多个实现了WebMvcConfigurer接口的类

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //注册拦截器 拦截规则
        //多个拦截器时 以此添加 执行顺序按添加顺序
        registry.addInterceptor(customInterceptor()).addPathPatterns("/*");
    }

    @Bean
    public HandlerInterceptor customInterceptor() {
        return new CustomHandlerInterceptor();
    }
}
