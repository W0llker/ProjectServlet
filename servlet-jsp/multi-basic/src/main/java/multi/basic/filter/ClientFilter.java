package multi.basic.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class ClientFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpSession session = req.getSession(false);
        if (session == null) {
            req.setAttribute("error", "Вы не прошли авторизацию");
            req.getRequestDispatcher("error/error.jsp").forward(req, servletResponse);
        } else if (session.getAttribute("userAuth") == null) {
            req.setAttribute("error", "Вы не прошли авторизацию");
            req.getRequestDispatcher("error/error.jsp").forward(req, servletResponse);
        } else {
            filterChain.doFilter(req, servletResponse);
        }
    }
}
