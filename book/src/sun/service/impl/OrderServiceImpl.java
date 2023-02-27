package sun.service.impl;

import sun.bean.Book;
import sun.bean.CartItem;
import sun.bean.Order;
import sun.bean.OrderItem;
import sun.dao.BookDao;
import sun.dao.OrderDao;
import sun.dao.OrderItemDao;
import sun.dao.impl.BookDaoImpl;
import sun.dao.impl.OrderDaoImpl;
import sun.dao.impl.OrderItemDaoImpl;
import sun.service.Cart;
import sun.service.OrderService;
import sun.utils.Page;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Map;

public class OrderServiceImpl implements OrderService {
    //订单Dao
    private OrderDao orderDao = new OrderDaoImpl();
    //订单项Dao
    private OrderItemDao orderItemDao = new OrderItemDaoImpl();
    //图书dao
    private BookDao bookDao = new BookDaoImpl();
    /*
    * 生成一个订单
    * 添加订单数据到order表中
    * 至少一个订单项 至多N个 所有订单项都添加到orderItem表中
    * 清空购物车中的数据
    * */
    @Override
    public String createOrder(Cart cart, Integer userId) throws SQLException {
        //添加订单数据到order表中
        String orderId = "" + System.currentTimeMillis() +userId;//没加userId 加了订单有null
        Order order = new Order();
        order.setOrderId(orderId);//订单编号
        order.setCreateTime(new Timestamp(System.currentTimeMillis()));//系统时间
        order.setPrice(cart.getTotalPrice());//订单总价格
        order.setStatus(0);//状态
        order.setUserId(userId);
        orderDao.save(order);
        //至少一个订单项 至多N个 所有订单项都添加到orderItem表中
        Map<Integer, CartItem> items = cart.getItems();
        for (Map.Entry<Integer, CartItem> entry : items.entrySet()) {
            OrderItem item = new OrderItem();
            item.setName(entry.getValue().getName());//设置订单项名字
            item.setCount(entry.getValue().getCount());//设置订单项数量
            item.setPrice(entry.getValue().getPrice());//设置订单项单价
            item.setTotalPrice(entry.getValue().getTotalPrice());//设置订单项总价
            item.setOrderId(orderId);//设置订单项的外键id 订单编号
            orderItemDao.save(item);
            //更新库存和销量
            Book book= bookDao.findById(entry.getValue().getId());//通过图书的主键id返回一个图书对象
            book.setSales(book.getSales()+entry.getValue().getCount());
            book.setStock(book.getStock()-entry.getValue().getCount());
            bookDao.updateById(book);//修改book的销量与库存
        }
        //清空购物车数据
        cart.clear();
        return orderId;
    }

    @Override
    public Page<Order> page(Integer pageNo, Integer pageSize) throws SQLException {
        //需要queryForpageToCount() , queryForPageTotalCount()方法
        Page<Order> page = new Page<>();
        Integer totalCount = orderDao.pageRecord();//获取总记录数
        page.setPageTotalCount(totalCount);//设置总记录数
        page.setPageTotal((totalCount + pageSize -1) / pageSize);
        page.setPageNo(pageNo);
        page.setItems(orderDao.page((page.getPageNo())));//设置分页查询结果集
        return page;
    }
}
