package me.test.springboottryit.service;

import me.test.springboottryit.SpringbootTryitApplication;
import org.junit.Before;
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
@SpringBootTest(classes = SpringbootTryitApplication.class)
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Before
    public void setUp() throws Exception {
        userService.deleteAll();
    }

    @Test
    public void test() {
        userService.create("a", 23);

        assert 23 == userService.getUserByName("a").getAge();

        userService.create("b", 2);
        assert 2 == userService.getUserCount();
    }
}