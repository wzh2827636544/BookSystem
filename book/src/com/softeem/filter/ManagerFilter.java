package com.softeem.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(dispatcherTypes = {DispatcherType.FORWARD,DispatcherType.INCLUDE,DispatcherType.REQUEST},filterName = "ManagerFilter",urlPatterns = "/pages/manager/*")
public class ManagerFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpSession session = httpServletRequest.getSession();
        Object user = session.getAttribute("user");
        if (user!=null){
            httpServletRequest.getRequestDispatcher("/login.jsp").forward(request,response);

        }else {
            chain.doFilter(request,response);
        }
    }
}
