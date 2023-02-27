package sun.servlet;

import sun.bean.Admin;
import sun.service.AdminService;
import sun.service.impl.AdminServiceImpl;
import sun.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

@WebServlet(name = "LoginServlet", value = "/LoginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, String[]> parameterMap = request.getParameterMap();
        Admin admin = new Admin();
        WebUtils.copyParamToBean(parameterMap,admin);
        request.setAttribute("user",admin);
        AdminService adminService = new AdminServiceImpl();
        try {
            Admin successAdmin = adminService.login(admin);
            if (successAdmin == null) {
                request.setAttribute("msg","用户名或密码不正确！");
                request.setAttribute("username", admin.getUsername());
                request.setAttribute("password", admin.getPassword());
                request.getRequestDispatcher("/pages/manager/login.jsp").forward(request, response);
            } else {
                Cookie nameCookie = new Cookie("username", successAdmin.getUsername());
                Cookie passCookie = new Cookie("password", successAdmin.getPassword());
                nameCookie.setMaxAge(60 * 60 *24 * 7);
                passCookie.setMaxAge(60 * 60 *24 * 7);
                response.addCookie(nameCookie);
                response.addCookie(passCookie);

                HttpSession session = request.getSession();
                session.setAttribute("user", successAdmin);//登陆成功后信息保存到session作用域
                //request.setAttribute("msg", "欢迎回来！");
                if(request.getParameter("sunurl") != null && !request.getParameter("sunurl").equals("")){
                    request.getRequestDispatcher(request.getParameter("sunurl")).forward(request,response);
                }else{
                    request.getRequestDispatcher("/pages/manager/manager.jsp").forward(request, response);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
