package sun.servlet;


import sun.bean.User;
import sun.service.UserService;
import sun.service.impl.UserServiceImpl;
import sun.utils.BaseServlet;
import sun.utils.CookieUtils;
import sun.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

@WebServlet(name = "UserServlet", value = "/UserServlet")
public class UserServlet extends BaseServlet {

    protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //String username = request.getParameter("username");
        //String password = request.getParameter("password");
        //String email = request.getParameter("email");
        //工具包代替这个操作
        Map<String, String[]> parameterMap = request.getParameterMap();
        User u = new User();
        WebUtils.copyParamToBean(parameterMap,u);
        request.setAttribute("user",u);
        UserService userService = new UserServiceImpl();
        try {
            User loginUser = userService.login(u);
            if (loginUser == null) {
                request.setAttribute("msg","用户名或密码不正确！");
                request.setAttribute("username", u.getUsername());
                request.setAttribute("password", u.getPassword());
                request.getRequestDispatcher("/pages/user/login.jsp").forward(request, response);
            } else {
                Cookie nameCookie = new Cookie("username", loginUser.getUsername());
                Cookie passCookie = new Cookie("password", loginUser.getPassword());
                nameCookie.setMaxAge(60 * 60 *24 * 7);
                passCookie.setMaxAge(60 * 60 *24 * 7);
                response.addCookie(nameCookie);
                response.addCookie(passCookie);

                HttpSession session = request.getSession();
                session.setAttribute("user", loginUser);//登陆成功后信息保存到session作用域
                request.setAttribute("msg", "欢迎回来！");
                if(request.getParameter("sunurl") != null && !request.getParameter("sunurl").equals("")){
                    request.getRequestDispatcher(request.getParameter("sunurl")).forward(request,response);
                }else{
                    request.getRequestDispatcher("/pages/user/success.jsp").forward(request, response);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void regist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取Session 中的验证码
        String token = (String) req.getSession().getAttribute(KAPTCHA_SESSION_KEY);
        // 删除 Session 中的验证码
        req.getSession().removeAttribute(KAPTCHA_SESSION_KEY);

        String code = req.getParameter("code");
        req.setAttribute("code", code);
        Map<String, String[]> parameterMap = req.getParameterMap();
        User u = new User();
        WebUtils.copyParamToBean(parameterMap,u);
        req.setAttribute("user",u);
        UserService userService = new UserServiceImpl();
        try {
            if (token.equalsIgnoreCase(code)) {
                if (!userService.existsUsername(u.getUsername())) {
                    userService.registUser(u);
                    HttpSession session = req.getSession();
                    session.setAttribute("user", u);
                    req.setAttribute("msg", "注册成功！");
                    req.getRequestDispatcher("pages/user/success.jsp").forward(req, resp);
                } else {
                    req.setAttribute("msg","用户名已被使用！");
                    req.getRequestDispatcher("pages/user/regist.jsp").forward(req, resp);
                }
            } else {
                req.setAttribute("msg","验证码不正确！");
                req.getRequestDispatcher("pages/user/regist.jsp").forward(req, resp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.invalidate();//cookie立即失效
        Cookie nameCookie = CookieUtils.findCookie("username", request.getCookies());
        Cookie passCookie = CookieUtils.findCookie("password", request.getCookies());
        if (nameCookie != null) {
            nameCookie.setMaxAge(0);
            response.addCookie(nameCookie);
        }
        if (passCookie != null) {
            nameCookie.setMaxAge(0);
            response.addCookie(passCookie);
        }
        response.sendRedirect("index.jsp");
    }
}
