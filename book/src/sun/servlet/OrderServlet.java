package sun.servlet;

import sun.bean.Order;
import sun.bean.User;
import sun.service.Cart;
import sun.service.OrderService;
import sun.service.impl.OrderServiceImpl;
import sun.utils.BaseServlet;
import sun.utils.Page;
import sun.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "OrderServlet", value = "/OrderServlet")
public class OrderServlet extends BaseServlet {

    protected void listOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        OrderService orderService = new OrderServiceImpl();
        int pageNo = WebUtils.parseInt(request.getParameter("pageNo"),1);
        int pageSize = WebUtils.parseInt(request.getParameter("pageSize"),4);
        try {
            Page<Order> page = orderService.page(pageNo, pageSize);
            page.setUrl("OrderServlet?action=listOrder&");
            request.setAttribute("page",page);
            request.getRequestDispatcher("/pages/order/order.jsp").forward(request,response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //生成订单
    protected void createOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Cart cart = (Cart)session.getAttribute("cart");
        User user = (User)session.getAttribute("user");
        System.out.println("检查点3：" + user);
        if (user == null) {
            request.setAttribute("msg","登录超时请重新登录");
            request.getRequestDispatcher("/pages/user/login.jsp").forward(request,response);
        }
        OrderService orderService = new OrderServiceImpl() ;
        try {
            String orderId = orderService.createOrder(cart, user.getId());
            session.setAttribute("orderId",orderId);
            //request.getRequestDispatcher("/pages/cart/checkout.jsp").forward(request,response);
            response.sendRedirect("pages/cart/checkout.jsp");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


}
