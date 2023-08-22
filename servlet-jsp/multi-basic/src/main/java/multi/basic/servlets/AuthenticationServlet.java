package multi.basic.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import multi.basic.config.ApplicationContext;
import multi.basic.repository.RepositoryClient;
import multi.domain.Client;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

public class AuthenticationServlet extends HttpServlet {
    /*
    Чуть позже добавлю эту реализацию в подходящие
    метод сервиса клиента
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var login = req.getParameter("login");
        var password = req.getParameter("password");
        RepositoryClient repositoryClient = ApplicationContext.getInstance().getRepositoryClient();
        Optional<Client> client = repositoryClient.getList().stream().filter(client1 -> client1.getLogin().equals(login) && client1.getPassword().equals(password)).findFirst();
        if (client.isEmpty()) {
            req.getRequestDispatcher("/authentication/Error.jsp").forward(req, resp);
        } else {
            req.setAttribute("user", client);
            //req.getRequestDispatcher("/authentication/Error.jsp").forward(req,resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter pw = resp.getWriter();
        resp.setContentType("application/json");
        Client test = new Client(req.getParameter("name"),
                req.getParameter("surName"),
                req.getParameter("login"),
                req.getParameter("password"));
        RepositoryClient repositoryClient = ApplicationContext.getInstance().getRepositoryClient();
        repositoryClient.save(test);
        pw.write(ApplicationContext.getInstance().getRepositoryClient().getList().toString());
    }
}
