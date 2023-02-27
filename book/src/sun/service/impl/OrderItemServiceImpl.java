package sun.service.impl;

import sun.bean.OrderItem;
import sun.dao.impl.OrderItemDaoImpl;
import sun.service.OrderItemService;

import java.sql.SQLException;
import java.util.List;

public class OrderItemServiceImpl implements OrderItemService {
    private OrderItemDaoImpl orderItemDaoImpl = new OrderItemDaoImpl();
    @Override
    public List<OrderItem> detailInfo(String id) throws SQLException {
        List<OrderItem> orderItems = orderItemDaoImpl.findByOrderId(id);
        return orderItems;
    }
}
