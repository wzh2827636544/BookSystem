package com.softeem.servlet;

import com.softeem.bean.Book;
import com.softeem.bean.Cart;
import com.softeem.service.BookService;
import com.softeem.service.CartItem;
import com.softeem.service.impl.BookServiceImpl;
import com.softeem.utils.BaseServlet;
import com.softeem.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "CartServlet", value = "/CartServlet")
public class CartServlet extends BaseServlet {
    protected void addItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BookService bookService = new BookServiceImpl();
        HttpSession session = request.getSession();
        int id = WebUtils.parseInt(request.getParameter("id"), 0);
        try {
            Book book = bookService.queryBookById(id);
            CartItem cartItem = new CartItem(book.getId(), book.getName(), 1, book.getPrice(), book.getPrice());
            Cart cart = (Cart) session.getAttribute("cart");
            session.setAttribute("lastName",book.getName());
            if (cart == null) {
                cart = new Cart();
                session.setAttribute("cart", cart);
            }
            cart.addItem(cartItem);
            response.sendRedirect(request.getHeader("Referer"));
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    protected void deleteItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = WebUtils.parseInt(request.getParameter("id"), 0);
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        cart.deleteItem(id);

        if (cart != null) {
            response.sendRedirect(request.getHeader("Referer"));
        }

    }

    protected void clear(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        if (cart != null) {
            cart.clear();
            response.sendRedirect(request.getHeader("Referer"));
        }
    }

    protected void updateCount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = WebUtils.parseInt(request.getParameter("id"),0);
        int count = WebUtils.parseInt(request.getParameter("count"),1);
        HttpSession session = request.getSession();
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        if (cart!=null){
            cart.updateCount(id,count);
        }
        response.sendRedirect(request.getHeader("Referer"));
    }
}
