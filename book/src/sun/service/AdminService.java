package sun.service;

import sun.bean.Admin;

import java.sql.SQLException;

public interface AdminService {
    /**
     *	登录
     *	@param user
     *	@return 如果返回null，说明登录失败，返回有值，是登录成功
     */
    public Admin login(Admin admin) throws SQLException;
}
