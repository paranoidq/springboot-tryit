package me.test.springboottryit.controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import me.test.springboottryit.domain.User;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * @author paranoidq
 * @since 1.0.0
 */
@RestController
@RequestMapping(path = "/users")
public class UserController {

    static Map<Long, User> users = Collections.synchronizedMap(new HashMap<>());

    @ApiOperation(value = "获取用户列表", notes = "")
    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<User> getUserList() {
        List<User> res = new ArrayList<>(users.values());
        return res;
    }


    @ApiOperation(value = "创建用户", notes = "根据User对象创建用户")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, dataTypeClass = User.class)
    })
    @RequestMapping(value = "", method = RequestMethod.POST)
    public String addUser(@RequestBody User user) {
        users.put(user.getId(), user);
        return "success";
    }

    //@ApiIgnore
    @ApiOperation(value = "获取用户", notes = "根据ID获取用户")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataTypeClass = Long.class)
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public User getUser(@PathVariable Long id) {
        return users.get(id);
    }


}
