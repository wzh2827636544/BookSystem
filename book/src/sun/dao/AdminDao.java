package sun.dao;

import sun.bean.Admin;
import sun.bean.Order;
import sun.utils.BaseInterface;

import java.sql.SQLException;

public interface AdminDao extends BaseInterface<Order> {
    /**
     *	根据 用户名和密码查询用户信息
     *	@param username
     *	@param password
     *	@return 如果返回null,说明用户名或密码错误,反之亦然
     */
    public Admin queryUserByUsernameAndPassword(String username, String password) throws SQLException;
}
