package sun.dao.impl;

import org.apache.commons.dbutils.handlers.BeanHandler;
import sun.bean.Admin;
import sun.bean.Order;
import sun.dao.AdminDao;
import sun.utils.BaseDao;

import java.sql.SQLException;
import java.util.List;

public class AdminDaoImpl extends BaseDao implements AdminDao {
    @Override
    public Admin queryUserByUsernameAndPassword(String username, String password) throws SQLException {
        String sql = "select * from t_admin where username = ? and password = ? ";
        Admin query = queryRunner.query(sql, new BeanHandler<>(Admin.class), username, password);
        System.out.println("成功用户：" + query);
        return query;
    }

    @Override
    public List<Order> findAll() throws SQLException {
        return null;
    }

    @Override
    public void save(Order order) throws SQLException {

    }

    @Override
    public void updateById(Order order) throws SQLException {

    }

    @Override
    public void deleteById(Integer id) throws SQLException {

    }

    @Override
    public Order findById(Integer id) throws SQLException {
        return null;
    }

    @Override
    public List<Order> page(Integer pageNumber) throws SQLException {
        return null;
    }

    @Override
    public Integer pageRecord() throws SQLException {
        return null;
    }
}
