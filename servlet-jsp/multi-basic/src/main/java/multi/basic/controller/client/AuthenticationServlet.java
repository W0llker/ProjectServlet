package multi.basic.controller.client;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import multi.api.contract.ClientApi;
import multi.api.contract.OrderApi;
import multi.api.contract.ProductApi;
import multi.api.dto.order.OrderResponse;
import multi.api.dto.user.UserResponse;
import multi.basic.exception.client.ClientDbException;
import multi.basic.config.ApplicationContext;
import multi.basic.exception.client.UserNullException;
import multi.domain.Role;

import java.io.IOException;

public class AuthenticationServlet extends HttpServlet {
    public final ClientApi clientService;
    public final OrderApi orderService;
    public final ProductApi productService;

    public AuthenticationServlet() {
        clientService = ApplicationContext.getInstance().getClientService();
        productService = ApplicationContext.getInstance().getProductService();
        orderService = ApplicationContext.getInstance().getOrderService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("index.jsp").include(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var login = req.getParameter("login");
        var password = req.getParameter("password");
        try {
            UserResponse user = clientService.authentication(login, password);
            preparation(req,user);
            if (user.getRole().equals(Role.CLIENT)) {
                req.getRequestDispatcher("/actionClient").forward(req, resp);
            } else {
                req.getRequestDispatcher("/actionAdmin").forward(req, resp);
            }
        } catch (UserNullException | ClientDbException e ) {
            req.setAttribute("error", e.getMessage());
            req.getRequestDispatcher("index.jsp").forward(req, resp);
        }
    }
    private void preparation(HttpServletRequest req, UserResponse user) {
        req.getSession(true).setAttribute("userAuth", user);
        req.getSession().setMaxInactiveInterval(10);
        OrderResponse orderResponse = orderService.createOrder(user.getId());
        req.getSession(false).setAttribute("order", orderResponse);
    }
}
