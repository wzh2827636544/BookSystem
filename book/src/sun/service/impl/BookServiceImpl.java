package sun.service.impl;

import sun.bean.Book;
import sun.dao.BookDao;
import sun.dao.impl.BookDaoImpl;
import sun.service.BookService;
import sun.utils.Page;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

public class BookServiceImpl implements BookService {
    private BookDao bookDao = new BookDaoImpl();


    @Override
    public void addBook(Book book) throws SQLException {
        bookDao.save(book);

    }

    @Override
    public void deleteBookById(Integer id) throws SQLException {
        bookDao.deleteById(id);
    }

    @Override
    public void updateBook(Book book) throws SQLException {
        bookDao.updateById(book);
    }

    @Override
    public Book queryBookById(Integer id) throws SQLException {
        return bookDao.findById(id);
    }

    @Override
    public List<Book> queryBooks() throws SQLException {
        List<Book> bookList = bookDao.findAll();
        System.out.println(bookList.size());
        return bookList;
    }

    @Override
    public Page<Book> page(int pageNo, int pageSize) throws SQLException {
        //需要queryForpageToCount() , queryForPageTotalCount()方法
        Page<Book> page = new Page<>();
        Integer totalCount = bookDao.queryForPageTotalCount();//获取总记录数
        page.setPageTotalCount(totalCount);//设置总记录数
        page.setPageTotal((totalCount + pageSize -1) / pageSize);
        page.setPageNo(pageNo);
        //设置分页查询结果集
        page.setItems(bookDao.queryForPageItems((page.getPageNo()-1)*pageSize,pageSize));
        return page;
    }

    @Override
    public Page<Book> page(int pageNo, int pageSize, String name, String author, BigDecimal min, BigDecimal max) throws SQLException {
        Page<Book> page = new Page<>();
        Integer totalCount = bookDao.queryForTotalCount(name,author,min,max);//获取总记录数
        page.setPageTotalCount(totalCount);//设置总记录数
        page.setPageTotal((totalCount + pageSize -1) / pageSize);
        page.setPageNo(pageNo);
        //设置分页查询结果集
        page.setItems(bookDao.queryForPageItems((page.getPageNo()-1)*pageSize,pageSize,name,author,min,max));
        return page;
    }

    @Override
    public Page<Book> pageByPrice(Integer pageNo, Integer pageSize, BigDecimal min, BigDecimal max) throws SQLException {
        Page<Book> page = new Page<>();
        Integer totalCount = bookDao.queryForTotalCount(min, max);
        page.setPageTotalCount(totalCount);//设置总记录数
        page.setPageTotal((totalCount + pageSize -1) / pageSize);
        page.setPageNo(pageNo);
        //设置分页查询结果集
        page.setItems(bookDao.queryForPageItems(min,max,(page.getPageNo()-1)*pageSize,pageSize));
        return page;
    }


}
