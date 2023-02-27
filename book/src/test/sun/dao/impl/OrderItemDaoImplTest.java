package test.sun.dao.impl;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import sun.bean.OrderItem;
import sun.dao.OrderItemDao;
import sun.dao.impl.OrderItemDaoImpl;

import java.math.BigDecimal;

/** 
* OrderItemDaoImpl Tester. 
* 
* @author <Authors name> 
* @since <pre>8月 8, 2022</pre> 
* @version 1.0 
*/ 
public class OrderItemDaoImplTest { 

@Before
public void before() throws Exception { 
} 

@After
public void after() throws Exception { 
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
* Method: save(OrderItem orderItem) 
* 
*/ 
@Test
public void testSave() throws Exception { 
//TODO: Test goes here...
    OrderItemDao orderItemDao = new OrderItemDaoImpl();

    orderItemDao.save(new OrderItem(null,"java从入门到精通", 1,new BigDecimal(100),new BigDecimal(100),"3324554"));
    orderItemDao.save(new OrderItem(null,"javaScript从入门到精通", 2,new BigDecimal(100),new BigDecimal(200),"3324554"));
    orderItemDao.save(new OrderItem(null,"Netty入门", 1,new BigDecimal(100),new BigDecimal(100),"3324554"));

} 

/** 
* 
* Method: updateById(OrderItem orderItem) 
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
