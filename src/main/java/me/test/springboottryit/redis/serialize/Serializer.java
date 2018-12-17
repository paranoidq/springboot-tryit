package me.test.springboottryit.redis.serialize;

import com.caucho.hessian.io.Hessian2Input;
import com.caucho.hessian.io.Hessian2Output;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Map;

/**
 * @author paranoidq
 * @since 1.0.0
 */
@Slf4j
public enum Serializer implements RedisSerializer<Object> {

    NATIVE { // 使用Redis内置的JDK序列化, 实体类必须实现Serializable接口
        private ThreadLocal<RedisSerializer> serializerThreadLocal = ThreadLocal.withInitial(() -> new JdkSerializationRedisSerializer());

        @Override
        public byte[] serialize(Object o) throws SerializationException {
            return serializerThreadLocal.get().serialize(o);
        }

        @Override
        public Object deserialize(byte[] bytes) throws SerializationException {
            return serializerThreadLocal.get().deserialize(bytes);
        }

    },
    JSON {  // 使用Redis内置的JSON序列化器
        private ThreadLocal<RedisSerializer> serializerThreadLocal = ThreadLocal.withInitial(() -> new GenericJackson2JsonRedisSerializer());

        @Override
        public byte[] serialize(Object o) throws SerializationException {
            return serializerThreadLocal.get().serialize(o);
        }

        @Override
        public Object deserialize(byte[] bytes) throws SerializationException {
            return serializerThreadLocal.get().deserialize(bytes);
        }
    },
    HESSIAN { // 使用Hessian序列化

        @Override
        public byte[] serialize(Object o) throws SerializationException {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            Hessian2Output output = new Hessian2Output(byteArrayOutputStream);
            try {
                output.writeObject(o);
                output.flush();
            } catch (IOException e) {
                log.error("Hessian serialize failed.", e);
            }
            return byteArrayOutputStream.toByteArray();
        }

        @Override
        public Object deserialize(byte[] bytes) throws SerializationException {
            if (bytes == null || bytes.length <= 0) {
                return null;
            }
            Hessian2Input input = new Hessian2Input(new ByteArrayInputStream(bytes));
            try {
                return input.readObject();
            } catch (IOException e) {
                log.error("Hessian deserialize failed.", e);
                return null;
            }
        }
    };

    private static Map<String, Serializer> map = Maps.newHashMapWithExpectedSize(Serializer.values().length);

    static {
        for (Serializer s : Serializer.values()) {
            map.put(s.name(), s);
        }
    }

    public static Serializer of(String prop) {
        if (map.containsKey(prop.toUpperCase())) {
            return map.get(prop.toUpperCase());
        }
        log.error("Unknown input serialize type: [" + prop + "]. Use default JSON serializer.");
        return JSON;
    }

}
