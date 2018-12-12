package me.test.springboottryit.service;

import me.test.springboottryit.domain.User;

/**
 * @author paranoidq
 * @since 1.0.0
 */
public interface UserService {

    void create(String name, Integer age);

    Integer getUserCount();

    User getUserById(Long id);

    User getUserByName(String name);

    void deleteAll();
}
