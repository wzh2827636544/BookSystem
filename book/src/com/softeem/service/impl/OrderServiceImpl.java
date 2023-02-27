package com.softeem.service.impl;

import com.softeem.bean.Book;
import com.softeem.bean.Cart;
import com.softeem.bean.Order;
import com.softeem.bean.OrderItem;
import com.softeem.dao.BookDao;
import com.softeem.dao.OrderDao;
import com.softeem.dao.OrderItemDao;
import com.softeem.dao.impl.BookDaoImpl;
import com.softeem.dao.impl.OrderDaoImpl;
import com.softeem.dao.impl.OrderItemDaoImpl;
import com.softeem.service.CartItem;
import com.softeem.service.OrderService;
import com.softeem.utils.Page;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Map;

public class OrderServiceImpl implements OrderService {


    private OrderDao orderDao = new OrderDaoImpl();
    private OrderItemDao orderItemDao = new OrderItemDaoImpl();
    private BookDao bookDao = new BookDaoImpl();
    @Override
    public String createOrder(Cart cart, Integer userId)throws SQLException {
        String orderId = ""+System.currentTimeMillis()+userId;
        Order order = new Order();
        order.setOrderId(orderId);//订单编号
        order.setCreateTime(new Timestamp(System.currentTimeMillis()));//系统时间
        order.setPrice(cart.getTotalPrice());//总价格
        order.setStatus(0);
        order.setUserId(userId);
        orderDao.save(order);
        Map<Integer, CartItem> items = cart.getItems();
        for (Map.Entry<Integer, CartItem> entry : items.entrySet()) {
            OrderItem orderItem = new OrderItem();
            orderItem.setName(entry.getValue().getName());
            orderItem.setCount(entry.getValue().getCount());
            orderItem.setPrice(entry.getValue().getPrice());
            orderItem.setTotalPrice(entry.getValue().getTotalPrice());
            orderItem.setOrderId(orderId);
            orderItemDao.save(orderItem);
            Book book = bookDao.findById(entry.getValue().getId());
            book.setSales(book.getSales()+entry.getValue().getCount());
            book.setStock(book.getStock()-entry.getValue().getCount());
            bookDao.updateById(book);
        }
        cart.clear();
        return orderId;
    }

    @Override
    public Page<Order> page(Integer pageNo,Integer pageSize) throws SQLException {
        Page<Order>page = new Page<>();
        Integer totalCount = orderDao.pageRecord();
        page.setPageTotalCount(totalCount);
        page.setPageTotal((totalCount+pageSize-1)/pageSize);
        page.setPageNo(pageNo);
        page.setItems(orderDao.page((page.getPageNo())));
        return page;
    }

//    @Override
//    public Page<OrderItem> pageById(String orderId, Integer pageNo, Integer pageSize) throws SQLException {
//        Page<OrderItem>page = new Page<>();
//        OrderItem orderItem = new OrderItem();
//        Integer totalCount = orderDao.pageRecord();
//        page.setPageTotalCount(totalCount);
//        page.setPageTotal((totalCount+pageSize-1)/pageSize);
//        page.setPageNo(pageNo);
//        page.setItems(orderItemDao.findByOrder(orderItem.getOrderId(),(page.getPageNo()-1)*pageSize,pageSize));
//        return page;
//    }
}