package test.sun.service.impl; 

import org.junit.Test; 
import org.junit.Before; 
import org.junit.After;
import sun.bean.User;
import sun.service.UserService;
import sun.service.impl.UserServiceImpl;

/** 
* UserServiceImpl Tester. 
* 
* @author <Authors name> 
* @since <pre>7月 22, 2022</pre> 
* @version 1.0 
*/ 
public class UserServiceImplTest {

    UserService userService = new UserServiceImpl();

    /**
* 
* Method: registUser(User user) 
* 
*/ 
@Test
public void testRegistUser() throws Exception { 
//TODO: Test goes here...
    userService.registUser(new User(null, "bbj168", "666666", "bbj168@qq.com"));
    userService.registUser(new User(null, "abc168", "666666", "abc168@qq.com"));
} 

/** 
* 
* Method: login(User user) 
* 
*/ 
@Test
public void testLogin() throws Exception { 
//TODO: Test goes here...
    System.out.println( userService.login(new User(null, "wzg168", "123456", null)) );
} 

/** 
* 
* Method: existsUsername(String username) 
* 
*/ 
@Test
public void testExistsUsername() throws Exception { 
//TODO: Test goes here...
    if (userService.existsUsername("wzg16888")) {
        System.out.println("用户名已存在！");
    } else {
        System.out.println("用户名可用！");
    }

} 


} 
