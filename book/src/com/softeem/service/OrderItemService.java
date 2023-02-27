package com.softeem.service;

import com.softeem.bean.OrderItem;

import java.sql.SQLException;
import java.util.List;

public interface OrderItemService {
    public List<OrderItem> detailInfo (String id) throws SQLException;
}
