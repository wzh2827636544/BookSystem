package com.softeem.test;

import com.softeem.bean.User;
import com.softeem.service.UserService;
import com.softeem.service.impl.UserServiceImpl;
import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.*;

public class UserServiceImplTest {

    UserService userService = new UserServiceImpl();
    @Test
    public void registUser() {
        try {
            userService.registUser(new User(null, "bbj168", "666666", "bbj168@qq.com"));
            userService.registUser(new User(null, "abc168", "666666", "abc168@qq.com"));
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void login() {
        try {
            System.out.println( userService.login(new User(null, "wzg168", "123456", null)) );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void existsUsername() {
        try {
            if (userService.existsUsername("wzg16888")) {
                System.out.println("用户名已存在！");
            } else {
                System.out.println("用户名可用！");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}