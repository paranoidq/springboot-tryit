package me.test.springboottryit.undertow;

import io.undertow.Undertow;
import io.undertow.UndertowOptions;
import org.springframework.boot.web.embedded.undertow.UndertowBuilderCustomizer;
import org.springframework.boot.web.embedded.undertow.UndertowServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author paranoidq
 * @since 1.0.0
 */
@Configuration
public class UndertowConfg {

    @Bean
    UndertowServletWebServerFactory embeddedServletContainerFactory() {
        UndertowServletWebServerFactory factory = new UndertowServletWebServerFactory();
        factory.addBuilderCustomizers(new UndertowBuilderCustomizer() {
            @Override
            public void customize(Undertow.Builder builder) {
                builder.setServerOption(
                    UndertowOptions.ENABLE_HTTP2, true
                );
                // ...
                // 通过代码进行其他设置
            }
        });
        return factory;
    }
}
