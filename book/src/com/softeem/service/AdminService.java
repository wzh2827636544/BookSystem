package com.softeem.service;

import com.softeem.bean.Admin;

import java.sql.SQLException;

public interface AdminService {

    public void registAdmin(Admin admin) throws SQLException;
    public Admin login(Admin admin) throws SQLException;
    public boolean existsUsername(String username) throws SQLException;
}
