package me.test.springboottryit.task.async;

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
@SpringBootTest(classes = SpringbootTryitApplication.class)
public class AsyncTaskTest {

    @Autowired
    private TestAsyncTask testAsyncTask;

    @Test
    public void test() throws Exception {

        testAsyncTask.doTaskOne();
        testAsyncTask.doTaskTwo();
        testAsyncTask.doTaskThree();

        Thread.currentThread().join();
    }

}