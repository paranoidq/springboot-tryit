package me.test.springboottryit.servlet;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.*;
import java.io.IOException;

/**
 * @author paranoidq
 * @since 1.0.0
 */
@Configuration
@Slf4j
public class FilterConfig {

    @Bean("filter1")
    public FilterRegistrationBean register() {

        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new Filter1());
        registrationBean.addUrlPatterns("/*");
        registrationBean.addInitParameter("paraKey", "paraValue");
        registrationBean.setName("filter1");
        registrationBean.setOrder(1);

        return registrationBean;
    }


    public class Filter1 implements Filter {

        @Override
        public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
            log.info("=== Servlet Filter invoked");
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

}
