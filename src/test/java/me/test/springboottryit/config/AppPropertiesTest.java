package me.test.springboottryit.config;

import me.test.springboottryit.SpringbootTryitApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author paranoidq
 * @since 1.0.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SpringbootTryitApplication.class, properties = {"spring.profiles.active=prod"})
public class AppPropertiesTest {

    @Autowired
    private AppProperties appProperties;


    @Test
    public void test() {
        assert appProperties.getTest() != null;
        System.out.println(appProperties.getTest());
    }
}