package me.test.springboottryit.service;

import me.test.springboottryit.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author paranoidq
 * @since 1.0.0
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void create(String name, Integer age) {
        jdbcTemplate.update("INSERT INTO User(name, age) values(?, ?)", name, age);
    }

    @Override
    public Integer getUserCount() {
        return jdbcTemplate.queryForObject("SELECT COUNT(1) from User", Integer.class);
    }

    @Override
    public User getUserById(Long id) {
        return jdbcTemplate.queryForObject(
            "SELECT * FROM User WHERE id = ?",
            new Object[]{id},
            // jdbcTemplate不会自动做orm映射
            new UserRowMapper());
    }

    @Override
    public User getUserByName(String name) {
        return jdbcTemplate.queryForObject(
            "SELECT * FROM User WHERE name = ?",
            new Object[]{name},
            // jdbcTemplate不会自动做orm映射
            new UserRowMapper());
    }

    @Override
    public void deleteAll() {
        jdbcTemplate.update("DELETE FROM User");
    }

    public static class UserRowMapper implements RowMapper<User> {
        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user = new User();
            user.setId(rs.getLong("id"));
            user.setName(rs.getString("name"));
            user.setAge(rs.getInt("age"));
            return user;
        }
    }
}
