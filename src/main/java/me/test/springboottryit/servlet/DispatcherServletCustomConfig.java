package me.test.springboottryit.servlet;

import org.springframework.context.annotation.Configuration;

/**
 * SpringBoot在使用SpringMvc的时候不需要配置DispatcherServlet的，因为已经自动配置了，
 * 但是如果想要加一些初始配置参数，可以通过如下方式解决
 * @author paranoidq
 * @since 1.0.0
 */
@Configuration
public class DispatcherServletCustomConfig {

//    @Bean
//    public ServletRegistrationBean dispatcherRegistration(DispatcherServlet dispatcherServlet) {
//        ServletRegistrationBean registration = new ServletRegistrationBean(dispatcherServlet);
//        registration.addUrlMappings("*.do");
//        registration.addUrlMappings("*.json");
//        return registration;
//    }
}
