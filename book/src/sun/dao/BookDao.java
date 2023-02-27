package sun.dao;


import sun.bean.Book;
import sun.utils.BaseInterface;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

public interface BookDao extends BaseInterface<Book> {
    public Integer queryForPageTotalCount() throws SQLException;

    public List<Book> queryForPageItems(int begin, int pageSize) throws SQLException;

    public Integer queryForTotalCount(BigDecimal min, BigDecimal max) throws SQLException;

    public List<Book> queryForPageItems ( BigDecimal min , BigDecimal max, Integer begin, Integer size) throws SQLException;

    public Integer queryForTotalCount(String name, String author , BigDecimal min, BigDecimal max) throws SQLException;

    public List<Book> queryForPageItems ( Integer begin, Integer size,String name, String author ,BigDecimal min, BigDecimal max) throws SQLException;
}