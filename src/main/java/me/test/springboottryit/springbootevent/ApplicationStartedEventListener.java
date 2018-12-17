package me.test.springboottryit.springbootevent;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;

/**
 * 由于Event触发时，容器尚未加载，所以无法通过注入bean的方式，只能通过配置META-INF告诉容器触发相应的listener
 * META-INF/spring.factories配置文件，通过配置org.springframework.context.ApplicationListener
 * @author paranoidq
 * @since 1.0.0
 */
@Slf4j
public class ApplicationStartedEventListener implements ApplicationListener<ApplicationStartedEvent> {

    @Override
    public void onApplicationEvent(ApplicationStartedEvent event) {
        log.info("=== SpringBoot ApplicationStartedEvent invoked ===");
    }
}
