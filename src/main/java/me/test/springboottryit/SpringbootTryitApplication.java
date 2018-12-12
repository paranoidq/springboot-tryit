package me.test.springboottryit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(scanBasePackages = "me.test")
@EnableScheduling
public class SpringbootTryitApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootTryitApplication.class, args);
    }
}
