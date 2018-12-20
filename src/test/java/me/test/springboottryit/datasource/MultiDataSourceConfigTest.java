package me.test.springboottryit.datasource;

import me.test.springboottryit.SpringbootTryitApplication;
import me.test.springboottryit.datasource.dao1.UserMapperDs1;
import me.test.springboottryit.datasource.dao2.UserMapperDs2;
import me.test.springboottryit.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;

/**
 * @author paranoidq
 * @since 1.0.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SpringbootTryitApplication.class)
public class MultiDataSourceConfigTest {

    @Autowired
    private UserMapperDs1 mapper1;

    @Autowired
    private UserMapperDs2 mapper2;


    @Test
    public void testMultiDatasource() {
        mapper1.deleteAll();
        mapper2.deleteAll();


        mapper1.insert(new User("qq", 1));
        mapper2.insert(new User("tt", 2));


        assertEquals("qq", mapper1.getAll().get(0).getName());
        assertEquals("tt", mapper2.getAll().get(0).getName());
    }
}