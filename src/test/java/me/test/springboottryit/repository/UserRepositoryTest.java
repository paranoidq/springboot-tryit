package me.test.springboottryit.repository;

import me.test.springboottryit.SpringbootTryitApplication;
import me.test.springboottryit.domain.User;
import org.junit.Assert;
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
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;


    @Test
    public void test() {
        userRepository.deleteAll();


        // 创建10条记录
        userRepository.save(new User("AAA", 10));
        userRepository.save(new User("BBB", 20));
        userRepository.save(new User("CCC", 30));
        userRepository.save(new User("DDD", 40));
        userRepository.save(new User("EEE", 50));
        userRepository.save(new User("FFF", 60));
        userRepository.save(new User("GGG", 70));
        userRepository.save(new User("HHH", 80));
        userRepository.save(new User("III", 90));
        userRepository.save(new User("JJJ", 100));

        Assert.assertEquals(10, userRepository.findAll().size());

        Assert.assertEquals(60, userRepository.findUser("FFF").getAge().longValue());

    }
}