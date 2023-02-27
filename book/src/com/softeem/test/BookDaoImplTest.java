package com.softeem.test;

import com.softeem.bean.Book;
import com.softeem.dao.BookDao;
import com.softeem.dao.impl.BookDaoImpl;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.*;

public class BookDaoImplTest {

    @Test
    public void findAll() {
    }

    @Test
    public void save() {
    }

    @Test
    public void updateById() {
    }

    @Test
    public void deleteById() {
    }

    @Test
    public void findById() {
    }

    @Test
    public void page() {
    }

    @Test
    public void pageRecord() {
    }

    @Test
    public void queryForPageTotalCount() throws SQLException {
        BookDao bookDao = new BookDaoImpl();
        List<Book>list = bookDao.queryForPageItems(1,5);
        for (Book book : list) {
            System.out.println(book);
        }
    }

    @Test
    public void queryForPageItems() {
    }
}