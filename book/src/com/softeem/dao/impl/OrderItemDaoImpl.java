package com.softeem.dao.impl;

import com.softeem.bean.OrderItem;
import com.softeem.dao.OrderItemDao;
import com.softeem.utils.BaseDao;
import org.apache.commons.dbutils.BasicRowProcessor;
import org.apache.commons.dbutils.BeanProcessor;
import org.apache.commons.dbutils.GenerousBeanProcessor;
import org.apache.commons.dbutils.RowProcessor;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class OrderItemDaoImpl extends BaseDao implements OrderItemDao {
    BeanProcessor bean = new GenerousBeanProcessor();
    RowProcessor processor = new BasicRowProcessor(bean);
    @Override
    public List<OrderItem> findAll() throws SQLException {
        return null;
    }

    @Override
    public void save(OrderItem orderItem) throws SQLException {
        String sql = "insert into t_order_item(`name`,`count`,`price`,`total_price`,`order_id`) values(?,?,?,?,?)";
        queryRunner.update(sql, orderItem.getName(), orderItem.getCount(), orderItem.getPrice(), orderItem.getTotalPrice(), orderItem.getOrderId());
    }

    @Override
    public void updateById(OrderItem orderItem) throws SQLException {

    }

    @Override
    public void deleteById(Integer id) throws SQLException {

    }

    @Override
    public OrderItem findById(Integer id) throws SQLException {
        OrderItem orderItem = new OrderItem();
        String sql = "select * from t_order_item where order_id = ?";

        return queryRunner.query(sql,new BeanHandler<OrderItem>(OrderItem.class,processor),orderItem.getOrderId());
    }

    @Override
    public List<OrderItem> page(Integer pageNumber) throws SQLException {
        String sql = "select * from t_order_item order by id desc limit ?,?";
        return queryRunner.query(sql,new BeanListHandler<OrderItem>(OrderItem.class,processor),(pageNumber-1)*pageSize,pageSize);

    }

    @Override
    public Integer pageRecord() throws SQLException {
        return null;
    }

    @Override
    public List<OrderItem> findByOrder(String orderId) throws SQLException {
        String sql = "select * from t_order_item where order_id = ?";

        return queryRunner.query(sql,new BeanListHandler<OrderItem>(OrderItem.class,processor),orderId);
    }
}
