package me.test.springboottryit.servlet;

import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author paranoidq
 * @since 1.0.0
 */
@Configuration
@ServletComponentScan
public class ServletScanConfig {

    /*
     * sb中要自动扫描servlet3.0中的@WebServlet、@WebFilter和@WebListener注解，必须添加@ServletComponentScan
     * 同时，只有在嵌入式容器中才会启用自动扫描
     */
}
