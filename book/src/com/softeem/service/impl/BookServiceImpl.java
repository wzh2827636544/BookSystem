package com.softeem.service.impl;

import com.softeem.bean.Book;
import com.softeem.dao.BookDao;
import com.softeem.dao.impl.BookDaoImpl;
import com.softeem.service.BookService;
import com.softeem.utils.Page;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

public class BookServiceImpl implements BookService {
    private BookDao bookDao = new BookDaoImpl();
    @Override
    public void addBook(Book book)  throws SQLException {
        bookDao.save(book);
    }

    @Override
    public void deleteBookById(Integer id)  throws SQLException{
        bookDao.deleteById(id);
    }

    @Override
    public void updateBook(Book book)  throws SQLException{
bookDao.updateById(book);
    }

    @Override
    public Book queryBookById(Integer id)  throws SQLException{
        return bookDao.findById(id);
    }

    @Override
    public List<Book> queryBooks()  throws SQLException{
        return bookDao.findAll();
    }

    @Override
    public Page<Book> page(int pageNo, int pageSize) throws SQLException {
        Page<Book>page = new Page<>();
        Integer totalCount = bookDao.queryForPageTotalCount();
        page.setPageTotalCount(totalCount);//设置总记录数
        page.setPageTotal((totalCount + pageSize - 1) / pageSize);
        page.setPageNo(pageNo);
        page.setItems(bookDao.queryForPageItems((page.getPageNo()-1)*pageSize,pageSize));//设置分页查询的结果集
        return page;
    }

    @Override
    public Page<Book> page(int pageNo, int pageSize,String name,String author,BigDecimal min, BigDecimal max) throws SQLException {
        Page<Book>page = new Page<>();
        Integer totalCount = bookDao.queryForPageTotalCount(name,author,min,max);
        page.setPageTotalCount(totalCount);
        page.setPageTotal((totalCount+pageSize-1)/pageSize);
        page.setPageNo(pageNo);
        page.setItems(bookDao.queryForPageItems((page.getPageNo()-1)*pageSize,pageSize,name,author,min, max));
        return page;
    }
}
