package multi.basic.controller.client;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import multi.api.contract.ClientApi;
import multi.api.dto.user.UserRequest;
import multi.basic.config.ApplicationContext;
import multi.basic.service.ClientService;

import java.io.IOException;

public class RegistrationServlet extends HttpServlet {
    private final ClientApi clientService;

    public RegistrationServlet() {
        clientService = ApplicationContext.getInstance().getClientService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("authentication/registration.jsp").include(req,resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var name = req.getParameter("name");
        var surName = req.getParameter("surName");
        var login = req.getParameter("login");
        var password = req.getParameter("password");
        if (name.equals("") || surName.equals("") || login.equals("") || password.equals("")) {
            req.setAttribute("error","Введите все параметры");
            req.getRequestDispatcher("authentication/registration.jsp").forward(req,resp);
        } else {
            clientService.createClient(new UserRequest(name,surName,login,password));
            req.getRequestDispatcher("authentication/registration.jsp").forward(req,resp);
        }
    }
}
