package me.test.springboottryit.datasource.dao2;

import me.test.springboottryit.domain.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author paranoidq
 * @since 1.0.0
 */
@Component
public interface UserMapperDs2 {

    @Select("SELECT * FROM user")
    @Results({
        // db与domain不一致时，显示指定字段和列的映射关系
        @Result(property = "name", column = "name", javaType = String.class)
    })
    List<User> getAll();

    @Insert("INSERT INTO user(name,age) VALUES(#{name}, #{age})")
    void insert(User user);

    @Delete("DELETE FROM user")
    void deleteAll();

}
