package me.test.springboottryit.endpoints;

import com.google.common.collect.Maps;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;


/**
 * 自定义endpoint
 *
 * 可以定义@ReadOperation读取运行时信息，也可以通过@WriteOperation操作运行时信息，实现动态控制
 * 这是一个不错的devops功能
 *
 * @author paranoidq
 * @since 1.0.0
 */
@ConfigurationProperties(prefix = "endpoints.servertime")
@Component
@Endpoint(id = "server-time", enableByDefault = true)
public class CustomEndpoint {

    @ReadOperation
    public Map<String, Object> hello() {
        Map<String, Object> result = Maps.newHashMap();
        Date dateTime = new Date();
        result.put("server_time", dateTime.toString());
        result.put("ms_format", dateTime.getTime());
        return result;
    }
}
