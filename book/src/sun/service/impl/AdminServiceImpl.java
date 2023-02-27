package sun.service.impl;

import sun.bean.Admin;
import sun.dao.AdminDao;
import sun.dao.impl.AdminDaoImpl;
import sun.service.AdminService;

import java.sql.SQLException;

public class AdminServiceImpl implements AdminService {
    private AdminDao adminDao = new AdminDaoImpl();
    @Override
    public Admin login(Admin admin) throws SQLException {
        Admin admin1 = adminDao.queryUserByUsernameAndPassword(admin.getUsername(), admin.getPassword());
        System.out.println("AdminServiceImpl: " + admin1);
        return admin1;
    }
}
