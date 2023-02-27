package sun.dao.impl;

import org.apache.commons.dbutils.handlers.BeanHandler;
import sun.bean.User;
import sun.dao.UserDao;
import sun.utils.BaseDao;

import java.sql.SQLException;
import java.util.List;

public class UserDaoImpl extends BaseDao implements UserDao {
    @Override
    public User queryUserByUsername(String username) throws SQLException{
        String sql = "select * from t_user where username = ? ";
        return queryRunner.query(sql,new BeanHandler<>(User.class),username);
    }

    @Override
    public User queryUserByUsernameAndPassword(String username, String password) throws SQLException {
        String sql = "select * from t_user where username = ? and password = ? ";
        User query = queryRunner.query(sql, new BeanHandler<>(User.class), username, password);
        System.out.println("成功用户：" + query);
        return query;
    }

    @Override
    public void saveUser(User user) throws SQLException {
        String sql = "insert into t_user values (null,?,?,?)";
        int result = queryRunner.update(sql,user.getUsername(),user.getPassword(),user.getEmail());
        //Long id = queryRunner.insert(sql,new ScalarHandler<Long>(),user.getUsername(),user.getPassword(),user.getEmail());
        //user.setId(id.intValue());

    }

    @Override
    public List<User> findAll() throws SQLException {

        return null;
    }

    @Override
    public void save(User user) throws SQLException {

    }

    @Override
    public void updateById(User user) throws SQLException {

    }

    @Override
    public void deleteById(Integer id) throws SQLException {

    }

    @Override
    public User findById(Integer id) throws SQLException {
        return null;
    }

    @Override
    public List<User> page(Integer pageNumber) throws SQLException {
        return null;
    }

    @Override
    public Integer pageRecord() throws SQLException {
        return null;
    }
}
