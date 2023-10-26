package chkir.resourciumoptimaii.web;

import chkir.resourciumoptimaii.dao.UserDAO;
import chkir.resourciumoptimaii.entities.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "UsersServlet", value = "/users")
public class UserServlet extends HttpServlet {



    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;
    private UserDAO userDAO;

    @Override
    public void init() throws ServletException {

        entityManagerFactory = Persistence.createEntityManagerFactory("resourceOptima");
        entityManager  = entityManagerFactory.createEntityManager();
        userDAO = new UserDAO(entityManager);
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<User> users = userDAO.getAllUsers();
        request.setAttribute("users", users);
        request.getRequestDispatcher("/WEB-INF/views/users/users.jsp").forward(request, response);
    }
}
