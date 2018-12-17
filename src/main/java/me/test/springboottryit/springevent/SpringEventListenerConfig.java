package me.test.springboottryit.springevent;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.context.event.EventListener;

/**
 * 参考： https://app.yinxiang.com/shard/s63/nl/13792677/3170c3dc-0c68-4136-a2bd-62d7b5df9ef7/
 *
 * 实现方式1：实现ApplicationListener接口
 * 实现方式2：注解@EventListener函数，函数参数为事件类型实例
 * @author paranoidq
 * @since 1.0.0
 */
@Slf4j
@Configuration
public class SpringEventListenerConfig {

//    @Component
//    public class ContextStartedListener implements ApplicationListener<ContextStartedEvent> {
//        @Override
//        public void onApplicationEvent(ContextStartedEvent event) {
//            log.info("=== Spring ContextStartedEvent invoked ===");
//        }
//    }


    @EventListener
    public void handleContextStartedEvent(ContextStartedEvent event) {
        log.info("=== Spring ContextStartedEvent invoked ===");
    }

    @EventListener
    public void handleContextRefreshedEvent(ContextRefreshedEvent event) {
        log.info("=== Spring ContextRefreshedEvent invoked ===");
    }

}
