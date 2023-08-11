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

public class RegistrationServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

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

        pw.write(test.toString());

    }
}
