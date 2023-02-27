package com.softeem.dao.impl;

import com.softeem.bean.Admin;
import com.softeem.dao.AdminDao;
import com.softeem.utils.BaseDao;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;

public class AdminDaoImpl extends BaseDao implements AdminDao {
    @Override
    public List<Admin> findAll() throws SQLException {
        return null;
    }

    @Override
    public void save(Admin admin) throws SQLException {
        String sql = "insert into t_admin values(null,?,?)";
        Long i = queryRunner.insert(sql, new ScalarHandler<Long>(), admin.getUsername(), admin.getPassword());
        admin.setId(i.intValue());
    }

    @Override
    public void updateById(Admin admin) throws SQLException {

    }

    @Override
    public void deleteById(Integer id) throws SQLException {

    }

    @Override
    public Admin findById(Integer id) throws SQLException {
        return null;
    }

    @Override
    public List<Admin> page(Integer pageNumber) throws SQLException {
        return null;
    }

    @Override
    public Integer pageRecord() throws SQLException {
        return null;
    }

    @Override
    public Admin queryAdminByUsername(String username) throws SQLException {
        String sql = "select * from t_admin where username = ? ";
        return queryRunner.query(sql,new BeanHandler<Admin>(Admin.class),username);
    }

    @Override
    public Admin queryAdminByUsernameAndPassword(String username, String password) throws SQLException {
        String sql = "select * from t_admin where username = ? and password = ?";
        return queryRunner.query(sql,new BeanHandler<Admin>(Admin.class),username,password);

    }
}
