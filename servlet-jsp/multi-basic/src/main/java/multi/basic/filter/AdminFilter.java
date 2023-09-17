package multi.basic.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import multi.api.dto.user.UserResponse;
import multi.domain.Role;

import java.io.IOException;

public class AdminFilter implements Filter {
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
            UserResponse user = (UserResponse) session.getAttribute("userAuth");
            if (user.getRole() == Role.ADMIN) filterChain.doFilter(req, servletResponse);
            else {
                req.setAttribute("error", "У вас не достаточно прав");
                req.getRequestDispatcher("error/error.jsp").forward(req, servletResponse);
            }
        }
    }
}
