package com.softeem.test;

import com.softeem.bean.Order;
import com.softeem.dao.OrderDao;
import com.softeem.dao.impl.OrderDaoImpl;
import org.junit.Test;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

public class OrderDaoTest {

@Test
public void saveOrder() throws SQLException {

OrderDao orderDao = new OrderDaoImpl();

orderDao.save(new Order("1234567891",new Timestamp(20000405),new BigDecimal(100),0, 1));

}
@Test
    public void page() throws SQLException {
        OrderDao orderDao = new OrderDaoImpl();
        List<Order> list = orderDao.page(1);
        for (Order order : list) {
            System.out.println(order);
        }
    }
}
