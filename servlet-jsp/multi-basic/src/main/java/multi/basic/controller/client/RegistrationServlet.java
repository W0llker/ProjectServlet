package multi.basic.controller.client;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import multi.api.contract.ClientApi;
import multi.api.dto.user.UserRequest;
import multi.api.exception.client.ClientDbException;
import multi.api.exception.client.ClientValidatorException;
import multi.basic.config.ApplicationContext;

import java.io.IOException;

public class RegistrationServlet extends HttpServlet {
    private final ClientApi clientService;

    public RegistrationServlet() {
        clientService = ApplicationContext.getInstance().getClientService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("authentication/registration.jsp").include(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var name = req.getParameter("name");
        var surName = req.getParameter("surName");
        var login = req.getParameter("login");
        var password = req.getParameter("password");
        try {
            clientService.createClient(new UserRequest(name, surName, login, password));
            req.getRequestDispatcher("authentication/registration.jsp").forward(req, resp);
        } catch (ClientValidatorException | ClientDbException e) {
            req.setAttribute("error", e.getMessage());
            req.getRequestDispatcher("authentication/registration.jsp").forward(req, resp);
        }
    }
}
