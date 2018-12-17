package me.test.springboottryit.endpoints.health;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

/**
 * @author paranoidq
 * @since 1.0.0
 */
@Component
public class CustomStatusHealthIndicator implements HealthIndicator {

    @Override
    public Health health() {
        int errorCode = check();
        if (errorCode != 200) {
            return Health.down()
                .withDetail("status", errorCode)
                .withDetail("message", "服务故障")
                .build();
        }
        return Health.up().build();
    }

    private int check() {
        return HttpStatus.OK.value();
    }
}
