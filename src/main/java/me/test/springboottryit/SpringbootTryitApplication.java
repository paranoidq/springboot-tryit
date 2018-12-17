package me.test.springboottryit;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.util.Arrays;

@SpringBootApplication(scanBasePackages = "me.test")
@EnableAspectJAutoProxy
public class SpringbootTryitApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(SpringbootTryitApplication.class, args);

        // 通过getBean方法到ApplicationArguments
        ApplicationArguments applicationArguments = context.getBean(ApplicationArguments.class);
    }

    @Bean
    public CommandLineRunnerBean dataLoader() {
        return new CommandLineRunnerBean();
    }


    /* 在springboot容器启动后，执行run方法
     * 多个CommandLineRunner同时存在，通过@Order标注顺序
     *
     *
     * 执行流：
     * ApplicationStartingEvent
     * ApplicationEnvironmentPreparedEvent
     * ApplicationPreparedEvent
     * ApplicationStartedEvent
     * CommandLineRunner ...
     * ApplicationReadyEvent
     *
     * -------------------
     *
     * ApplicationFailedEvent
     */
    @Slf4j
    static class CommandLineRunnerBean implements CommandLineRunner {
        @Override
        public void run(String... args) throws Exception {
            System.out.println("=== 应用启动成功===" + Arrays.toString(args));
        }
    }


    /*
     * 区别在于ApplicationRunner的run方法参数时经过处理的，而不是原始的字符串数组
     */
    @Slf4j
    static class ApplicationRunnerBean implements ApplicationRunner {

        @Override
        public void run(ApplicationArguments args) throws Exception {
            args.getSourceArgs(); // 获得CommandLineRunner一样的原始参数
        }
    }
}
