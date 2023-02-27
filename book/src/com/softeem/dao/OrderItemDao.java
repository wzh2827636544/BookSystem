package com.softeem.dao;

import com.softeem.bean.OrderItem;
import com.softeem.utils.BaseInterface;

import java.sql.SQLException;
import java.util.List;

public interface OrderItemDao extends BaseInterface<OrderItem> {
    public List<OrderItem> findByOrder(String orderId) throws SQLException;
}
