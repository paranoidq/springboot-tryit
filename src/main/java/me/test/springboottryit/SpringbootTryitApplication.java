package me.test.springboottryit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication(scanBasePackages = "me.test")
@EnableScheduling
@EnableAspectJAutoProxy
@EnableCaching
public class SpringbootTryitApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootTryitApplication.class, args);
    }
}
