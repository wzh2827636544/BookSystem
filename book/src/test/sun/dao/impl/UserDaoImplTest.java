package test.sun.dao.impl; 

import org.junit.Test; 
import org.junit.Before; 
import org.junit.After;
import sun.bean.User;
import sun.dao.UserDao;
import sun.dao.impl.UserDaoImpl;

/** 
* UserDaoImpl Tester. 
* 
* @author <Authors name> 
* @since <pre>7ÔÂ 22, 2022</pre> 
* @version 1.0 
*/ 
public class UserDaoImplTest { 

private UserDao userDao = new UserDaoImpl();

/** 
* 
* Method: queryUserByUsername(String username) 
* 
*/ 
@Test
public void testQueryUserByUsername() throws Exception { 
//TODO: Test goes here...
    User user = userDao.queryUserByUsername("admin");
    System.out.println("user = " + user);
} 

/** 
* 
* Method: queryUserByUsernameAndPassword(String username, String password) 
* 
*/ 
@Test
public void testQueryUserByUsernameAndPassword() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: saveUser(User user) 
* 
*/ 
@Test
public void testSaveUser() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: findAll() 
* 
*/ 
@Test
public void testFindAll() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: save(User user) 
* 
*/ 
@Test
public void testSave() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: updateById(User user) 
* 
*/ 
@Test
public void testUpdateById() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: deleteById(Integer id) 
* 
*/ 
@Test
public void testDeleteById() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: findById(Integer id) 
* 
*/ 
@Test
public void testFindById() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: page(Integer pageNumber) 
* 
*/ 
@Test
public void testPage() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: pageRecord() 
* 
*/ 
@Test
public void testPageRecord() throws Exception { 
//TODO: Test goes here... 
} 


} 
