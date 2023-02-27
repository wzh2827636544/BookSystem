package com.softeem.test;

import com.softeem.bean.User;
import com.softeem.dao.UserDao;
import com.softeem.dao.impl.UserDaoImpl;
import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.*;

public class UserDaoImplTest {
    private UserDao userDao = new UserDaoImpl();

    @Test
    public void queryUserByUsername() {
        try {
            User user = userDao.queryUserByUsername("admin");
            System.out.println("user = " + user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void queryUserByUsernameAndPassword() {
        try {
            User user = userDao.queryUserByUsernameAndPassword("admin", "admin");
            System.out.println("user = " + user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void findAll() {
    }

    @Test
    public void save() {
        User user = new User();
        user.setUsername("王志浩");
        user.setPassword("123456");
        user.setEmail("123@qq.com");
        try {
            userDao.save(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void updateById() {
    }

    @Test
    public void deleteById() {
    }

    @Test
    public void findById() {
    }

    @Test
    public void page() {
    }

    @Test
    public void pageRecord() {
    }
}