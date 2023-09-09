package multi.basic.controller.client;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import multi.api.contract.ClientApi;
import multi.api.contract.ProductApi;
import multi.basic.config.ApplicationContext;
import multi.basic.service.ClientService;
import multi.basic.service.ProductService;
import multi.domain.Role;

import java.io.IOException;

public class AuthenticationServlet extends HttpServlet {
    public final ClientApi clientService;
    public final ProductApi productService;
    public AuthenticationServlet() {
        clientService = ApplicationContext.getInstance().getClientService();
        productService = ApplicationContext.getInstance().getProductServicel();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("index.jsp").include(req,resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var login = req.getParameter("login");
        var password = req.getParameter("password");
        if(login.equals("") || password.equals("")) {
            req.setAttribute("error","Вы не задали все параметры");
            req.getRequestDispatcher("index.jsp").forward(req,resp);
        }
        var user = clientService.authentication(login,password);
        if (user == null) {
            req.setAttribute("error","Извините.Ваш логин или пароль неправильный");
            req.getRequestDispatcher("index.jsp").forward(req,resp);
        } else if (user.getRole().equals(Role.CLIENT)) {
            req.getSession(true).setAttribute("userAuth",user);
            req.setAttribute("products",productService.showProduct());
            req.getRequestDispatcher("authentication/client.jsp").forward(req,resp);
        } else {
            req.getSession(true).setAttribute("userAuth",user);
            req.getRequestDispatcher("/actionAdmin").forward(req,resp);
        }
    }
}
