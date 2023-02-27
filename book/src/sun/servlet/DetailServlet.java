package sun.servlet;

import sun.bean.OrderItem;
import sun.service.OrderItemService;
import sun.service.impl.OrderItemServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "DetailServlet", value = "/DetailServlet")
public class DetailServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String orderId = req.getParameter("orderId");
        OrderItemService orderItemService = new OrderItemServiceImpl();
        try {
            List<OrderItem> orderItems = orderItemService.detailInfo(orderId);
            System.out.println("orderItems = " + orderItems);
            System.out.println("orderItemsSize = " + orderItems.size());
            req.setAttribute("orderItems",orderItems);
            req.getRequestDispatcher("/pages/order/detail.jsp").forward(req,resp);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
    protected void detailInfo(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
