package sun.service;

import sun.bean.OrderItem;

import java.sql.SQLException;
import java.util.List;

public interface OrderItemService {
    public List<OrderItem> detailInfo (String id) throws SQLException;
}
