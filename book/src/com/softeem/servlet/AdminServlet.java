package com.softeem.servlet;

import com.softeem.bean.Admin;
import com.softeem.service.AdminService;
import com.softeem.service.impl.AdminServiceImpl;
import com.softeem.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

@WebServlet(name = "AdminServlet", value = "/AdminServlet")
public class AdminServlet extends BookServlet {

        private AdminService adminService = new AdminServiceImpl();

    protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        Admin admin = WebUtils.copyParamToBean(request.getParameterMap(),new Admin());
//        try {
//            Admin myadmin = adminService.login(admin);
//            if (myadmin !=null){
//                HttpSession session = request.getSession();
//                session.setAttribute("admin",myadmin);
//                request.getRequestDispatcher("/pages/manager/manager.jsp").forward(request,response);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

        request.setCharacterEncoding("UTF-8");
        Map<String, String[]> parameterMap = request.getParameterMap();
        Admin admin = new Admin();
        WebUtils.copyParamToBean(parameterMap, admin);

        try {
            Admin myadmin = adminService.login(admin);
            if (myadmin == null) {

                request.setAttribute("msg", "账号名或密码输入不正确！！");
                request.setAttribute("username", admin.getUsername());
                request.setAttribute("password", admin.getPassword());
                request.getRequestDispatcher("login.jsp").forward(request, response);
            } else {
                Cookie usernameCookie = new Cookie("username", myadmin.getUsername());
                Cookie passwordCookie = new Cookie("password", myadmin.getPassword());
                usernameCookie.setMaxAge(60 * 60 * 24 * 7);
                passwordCookie.setMaxAge(60 * 60 * 24 * 7);
                response.addCookie(usernameCookie);
                response.addCookie(passwordCookie);
                HttpSession session = request.getSession();
                session.setAttribute("admin", myadmin);
                request.setAttribute("msg", "欢迎回来！！");
                if (request.getParameter("wangUrl") != null && !request.getParameter("wangUrl").equals("")) {
                    request.getRequestDispatcher("index.jsp").forward(request, response);
                }
                request.getRequestDispatcher("/pages/manager/manager.jsp").forward(request, response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

//    protected void regist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        HttpSession session = request.getSession();
//        // 获取Session 中的验证码
//        String token = (String) session.getAttribute(KAPTCHA_SESSION_KEY);
//// 删除 Session 中的验证码
//        session.removeAttribute(KAPTCHA_SESSION_KEY);
//
//        String code = request.getParameter("code");
////
//        request.setAttribute("code", code);
//        Map<String, String[]> parameterMap = request.getParameterMap();
//        Admin admin = new Admin();
//        WebUtils.copyParamToBean(parameterMap, admin);
//        request.setAttribute("a", admin);
//        try {
//            if (token.equalsIgnoreCase(code)) {
//                if (!adminService.existsUsername(admin.getUsername())) {
//                    adminService.registAdmin(admin);
//                    session.setAttribute("admin", admin);
//                    request.setAttribute("msg", "注册成功！！");
//                    request.getRequestDispatcher("/pages/manager/success.jsp").forward(request, response);
//                } else {
//                    request.setAttribute("msg", "用户名已存在请更换");
//                    request.getRequestDispatcher("/pages/manager/regist.jsp").forward(request, response);
//                }
//            } else {
//                request.setAttribute("msg", "验证码输入错误，请重新输入！！");
//                request.getRequestDispatcher("/pages/manager/regist.jsp").forward(request, response);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    protected void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        //	1、销毁Session 中用户登录的信息（或者销毁Session）
//        request.getSession().invalidate();
//        Cookie usernameCookie = CookieUtils.findCookie("username", request.getCookies());
//        Cookie passwordCookie = CookieUtils.findCookie("password", request.getCookies());
//        if (usernameCookie != null) {
//            usernameCookie.setMaxAge(0);
//            response.addCookie(usernameCookie);
//        }
//        if (passwordCookie != null) {
//            passwordCookie.setMaxAge(0);
//            response.addCookie(passwordCookie);
//        }
////	2、重定向到首页（或登录页面）。
//        response.sendRedirect("index.jsp");
//    }

}
