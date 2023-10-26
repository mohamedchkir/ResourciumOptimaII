package chkir.resourciumoptimaii.web;

import chkir.resourciumoptimaii.dao.TaskDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "DeleteTaskServlet" ,value = "/deleteTask")
public class DeleteTaskServlet extends HttpServlet {
    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;
    private TaskDAO taskDAO;

    @Override
    public void init() throws ServletException {
        entityManagerFactory = Persistence.createEntityManagerFactory("resourceOptima");
        entityManager = entityManagerFactory.createEntityManager();
        taskDAO = new TaskDAO(entityManager);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the task ID to be deleted from the request parameter.
        String taskIdParam = request.getParameter("taskId");

        if (taskIdParam != null) {
            try {
                int taskId = Integer.parseInt(taskIdParam);

                // Delete the task by ID.
                taskDAO.deleteTask(taskId);
            } catch (NumberFormatException e) {
                // Handle the case where the taskId parameter is not a valid number.
                // You can add an error message or redirect to an error page.
                e.printStackTrace();
            }
        }

        // Redirect to a page after task deletion (e.g., tasks list or another page).
        response.sendRedirect(request.getContextPath() + "/tasks");
    }

}
