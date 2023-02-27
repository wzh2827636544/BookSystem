package sun.service;

import sun.bean.Book;
import sun.utils.Page;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

public interface BookService {

    public void addBook(Book book) throws SQLException;

    public void deleteBookById(Integer id) throws SQLException;
    public void updateBook(Book book) throws SQLException;
    public Book queryBookById(Integer id) throws SQLException;
    public List<Book> queryBooks() throws SQLException;
    public Page<Book> page(int pageNo, int pageSize) throws SQLException;
    public Page<Book> page(int pageNo, int pageSize,String name,String author, BigDecimal min , BigDecimal max) throws SQLException;
    public Page pageByPrice(Integer pageNo, Integer pageSize , BigDecimal min , BigDecimal max) throws SQLException;
}
