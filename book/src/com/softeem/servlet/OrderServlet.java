package com.softeem.servlet;

import com.softeem.bean.Cart;
import com.softeem.bean.Order;
import com.softeem.bean.OrderItem;
import com.softeem.bean.User;
import com.softeem.service.OrderItemService;
import com.softeem.service.OrderService;
import com.softeem.service.impl.OrderItemServiceImpl;
import com.softeem.service.impl.OrderServiceImpl;
import com.softeem.utils.Page;
import com.softeem.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "OrderServlet",value = "/OrderServlet")
public class OrderServlet extends BookServlet {
    protected void createOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        User user = (User) session.getAttribute("user");
        if (user == null) {
            request.setAttribute("msg", "登陆超时，请重新登录~~");
            request.getRequestDispatcher("/pages/user/login.jsp").forward(request, response);
        }
        OrderService orderService = new OrderServiceImpl();
        try {
            String orderId = orderService.createOrder(cart, user.getId());
            session.setAttribute("orderId", orderId);
            response.sendRedirect(request.getContextPath() + "/pages/cart/checkout.jsp");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void listOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        OrderService orderService = new OrderServiceImpl();
        int pageNo = WebUtils.parseInt(request.getParameter("pageNo"), 1);
        int pageSize = WebUtils.parseInt(request.getParameter("pageSize"), 4);
        try {
            Page<Order> page = orderService.page(pageNo, pageSize);
            page.setUrl("OrderServlet?action=listOrder&");
            request.setAttribute("page", page);
            request.getRequestDispatcher("/pages/order/order.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void OrderDetails(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String orderId = request.getParameter("orderId");
        OrderItemService orderItemService = new OrderItemServiceImpl();
        try {
            List<OrderItem> orderItems = orderItemService.detailInfo(orderId);
            System.out.println("orderItems = " + orderItems);
            System.out.println("orderItemsSize = " + orderItems.size());
            request.setAttribute("orderItems", orderItems);
            request.getRequestDispatcher("/pages/order/detail.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
