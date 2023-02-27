package sun.utils;

import org.apache.commons.dbutils.QueryRunner;

/**
 * BaseDao的目地就是去优化dao实现类
 */
public abstract class BaseDao {
    public QueryRunner queryRunner ;
    public int pageSize = 4 ;
    public BaseDao(){
        queryRunner = new QueryRunner(MyDataSource.getDataSource());
    }
}
