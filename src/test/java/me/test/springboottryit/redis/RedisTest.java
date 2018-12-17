package me.test.springboottryit.redis;

import me.test.springboottryit.SpringbootTryitApplication;
import me.test.springboottryit.domain.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.concurrent.TimeUnit;

/**
 * @author paranoidq
 * @since 1.0.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SpringbootTryitApplication.class, properties = {"spring.profiles.active=prod"})
public class RedisTest {

    @Autowired
    private RedisUtil redisUtil;

    @Test
    public void test() {
        redisUtil.set("aaa", "111");
        Assert.assertEquals("111", redisUtil.get("aaa"));
    }


    @Test
    public void testObj() throws InterruptedException {
        User user = new User("qq", 12);

        redisUtil.set("com.neox", user);
        redisUtil.set("com.neo.f", user, 1, TimeUnit.SECONDS);

        TimeUnit.SECONDS.sleep(1);

        assert redisUtil.hasKey("com.neox.f") == false;
    }

}
