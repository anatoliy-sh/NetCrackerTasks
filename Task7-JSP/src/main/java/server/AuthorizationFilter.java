package server;

import db.MyConnection;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Анатолий on 04.02.2016.
 */
@WebFilter(filterName = "authorizationFilter", servletNames = {"TimeTableServlet"})
public class AuthorizationFilter implements Filter {

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        if (check(request)) {
            filterChain.doFilter(request, response);
        } else response.sendRedirect("index.html");

    }

    public boolean check(HttpServletRequest request){
        HttpSession session = request.getSession();
        if(session.getAttribute("login") != null
                && session.getAttribute("login").toString().equals("Tolik")
                && session.getAttribute("password") != null
                && session.getAttribute("password").toString().equals("111")){
            return true;
        }
        return false;
    }

    public void destroy() {
    }

    public void init(FilterConfig filterConfig) throws ServletException {
    }
}