package sun.service.impl;

import sun.bean.User;
import sun.dao.UserDao;
import sun.dao.impl.UserDaoImpl;
import sun.service.UserService;

import java.sql.SQLException;

public class UserServiceImpl implements UserService {
    private UserDao userDao = new UserDaoImpl();
    @Override
    public void registUser(User user) throws SQLException {
        userDao.saveUser(user);
    }

    @Override
    public User login(User user) throws SQLException {
        User user1 = userDao.queryUserByUsernameAndPassword(user.getUsername(), user.getPassword());
        System.out.println("检查点2：" + user1);
        return user1;
    }

    @Override
    public boolean existsUsername(String username) throws SQLException {
        User user = userDao.queryUserByUsername(username);
        return user == null ? false : true;
    }
}
