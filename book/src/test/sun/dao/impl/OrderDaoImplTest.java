package test.sun.dao.impl;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import sun.bean.Order;
import sun.dao.OrderDao;
import sun.dao.impl.OrderDaoImpl;

import java.math.BigDecimal;
import java.sql.Timestamp;

/** 
* OrderDaoImpl Tester. 
* 
* @author <Authors name> 
* @since <pre>8ÔÂ 8, 2022</pre> 
* @version 1.0 
*/ 
public class OrderDaoImplTest { 

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
* Method: save(Order order) 
* 
*/ 
@Test
public void testSave() throws Exception {
//TODO: Test goes here...
    Order order = new Order(""+System.currentTimeMillis(),new Timestamp(System.currentTimeMillis()),new BigDecimal(10),0,1);
    OrderDao orderDao = new OrderDaoImpl();
    orderDao.save(order);
}

/** 
* 
* Method: updateById(Order order) 
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
