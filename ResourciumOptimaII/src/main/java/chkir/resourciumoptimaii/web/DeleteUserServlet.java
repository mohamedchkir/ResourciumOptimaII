package chkir.resourciumoptimaii.web;

import chkir.resourciumoptimaii.dao.UserDAO;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "DeleteUserServlet", value = "/deleteUser")
public class DeleteUserServlet extends HttpServlet {

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
        // Get the user ID to be deleted from the request parameter.
        String userIdParam = request.getParameter("userId");

        if (userIdParam != null) {
            try {
                Long userId = Long.parseLong(userIdParam);

                // Delete the user by ID.
                userDAO.deleteUser(userId);
            } catch (NumberFormatException e) {
                // Handle the case where the userId parameter is not a valid number.
                // You can add an error message or redirect to an error page.
                e.printStackTrace();
            }
        }

        // Redirect to a page after user deletion (e.g., users list or another page).
        response.sendRedirect(request.getContextPath() + "/dashboard");
    }
}
