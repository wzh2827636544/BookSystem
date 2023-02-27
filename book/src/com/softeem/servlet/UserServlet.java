package com.softeem.servlet;

import com.softeem.bean.User;
import com.softeem.service.UserService;
import com.softeem.service.impl.UserServiceImpl;
import com.softeem.utils.BaseServlet;
import com.softeem.utils.CookieUtils;
import com.softeem.utils.WebUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

@WebServlet(name = "UserServlet", value = "/UserServlet")
public class UserServlet extends BaseServlet {
    UserService userService = new UserServiceImpl();

    protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        Map<String, String[]> parameterMap = request.getParameterMap();
        User user = new User();
        WebUtils.copyParamToBean(parameterMap, user);

        try {
            User myuser = userService.login(user);
            if (myuser == null) {

                request.setAttribute("msg", "账号名或密码输入不正确！！");
                request.setAttribute("username", user.getUsername());
                request.setAttribute("password", user.getPassword());
                request.getRequestDispatcher("/pages/user/login.jsp").forward(request, response);
            } else {
                Cookie usernameCookie = new Cookie("username", myuser.getUsername());
                Cookie passwordCookie = new Cookie("password", myuser.getPassword());
                usernameCookie.setMaxAge(60 * 60 * 24 * 7);
                passwordCookie.setMaxAge(60 * 60 * 24 * 7);
                response.addCookie(usernameCookie);
                response.addCookie(passwordCookie);
                HttpSession session = request.getSession();
                session.setAttribute("user", myuser);
                request.setAttribute("msg", "欢迎回来！！");
                if (request.getParameter("wangUrl") != null && !request.getParameter("wangUrl").equals("")) {
                    request.getRequestDispatcher("index.jsp").forward(request, response);
                }
                //response.sendRedirect("/pages/user/success.jsp");
                request.getRequestDispatcher("/pages/user/success.jsp").forward(request, response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void regist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        // 获取Session 中的验证码
        String token = (String) session.getAttribute(KAPTCHA_SESSION_KEY);
// 删除 Session 中的验证码
        session.removeAttribute(KAPTCHA_SESSION_KEY);

        String code = request.getParameter("code");
//
        request.setAttribute("code", code);
        Map<String, String[]> parameterMap = request.getParameterMap();
        User user = new User();
        WebUtils.copyParamToBean(parameterMap, user);
        request.setAttribute("u", user);
        UserService userService = new UserServiceImpl();
        try {
            if (token.equalsIgnoreCase(code)) {
                if (!userService.existsUsername(user.getUsername())) {
                    userService.registUser(user);
                    session.setAttribute("user", user);
                    request.setAttribute("msg", "注册成功！！");
                    request.getRequestDispatcher("/pages/user/success.jsp").forward(request, response);
                } else {
                    request.setAttribute("msg", "用户名已存在请更换");
                    request.getRequestDispatcher("/pages/user/regist.jsp").forward(request, response);
                }
            } else {
                request.setAttribute("msg", "验证码输入错误，请重新输入！！");
                request.getRequestDispatcher("/pages/user/regist.jsp").forward(request, response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //	1、销毁Session 中用户登录的信息（或者销毁Session）
        request.getSession().invalidate();
        Cookie usernameCookie = CookieUtils.findCookie("username", request.getCookies());
        Cookie passwordCookie = CookieUtils.findCookie("password", request.getCookies());
        if (usernameCookie != null) {
            usernameCookie.setMaxAge(0);
            response.addCookie(usernameCookie);
        }
        if (passwordCookie != null) {
            passwordCookie.setMaxAge(0);
            response.addCookie(passwordCookie);
        }
//	2、重定向到首页（或登录页面）。
        response.sendRedirect("index.jsp");
    }

}
