package me.test.springboottryit.servlet;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;

/**
 * @author paranoidq
 * @since 1.0.0
 */
@Configuration
@Slf4j
public class ListenerConfig {

    @Bean("listener1")
    ServletListenerRegistrationBean register() {
        ServletListenerRegistrationBean registrationBean = new ServletListenerRegistrationBean(new Listener1());
        return registrationBean;
    }

    public class Listener1 implements ServletRequestListener {

        @Override
        public void requestDestroyed(ServletRequestEvent sre) {
            log.info("Servlet request destroyed");
        }

        @Override
        public void requestInitialized(ServletRequestEvent sre) {
            log.info("Servlet request initialized");
        }
    }
}
