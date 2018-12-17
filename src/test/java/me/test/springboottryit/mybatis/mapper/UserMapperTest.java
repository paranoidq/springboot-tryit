package me.test.springboottryit.mybatis.mapper;

import me.test.springboottryit.SpringbootTryitApplication;
import me.test.springboottryit.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * @author paranoidq
 * @since 1.0.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SpringbootTryitApplication.class, properties = {"spring.profiles.active=prod"})
public class UserMapperTest {


    @Autowired
    private UserMapper userMapper;

    @Test
    public void test() {
        userMapper.deleteAll();

        userMapper.insert(new User("qq", 11));

        List<User> users = userMapper.getAll();
        assert 1 == users.size();
        assert "qq".equals(users.get(0).getName());
    }
}