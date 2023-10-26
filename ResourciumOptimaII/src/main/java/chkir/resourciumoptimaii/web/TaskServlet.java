package chkir.resourciumoptimaii.web;

import chkir.resourciumoptimaii.dao.TaskDAO;
import chkir.resourciumoptimaii.dao.UserDAO;
import chkir.resourciumoptimaii.entities.Task;
import chkir.resourciumoptimaii.entities.User;
import chkir.resourciumoptimaii.enums.Priority;
import chkir.resourciumoptimaii.enums.Status;
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

@WebServlet(name = "TaskServlet", value = "/tasks")
public class TaskServlet extends HttpServlet {

    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;
    private TaskDAO taskDAO;
    private UserDAO userDAO;

    @Override
    public void init() throws ServletException {
        entityManagerFactory = Persistence.createEntityManagerFactory("resourceOptima");
        entityManager = entityManagerFactory.createEntityManager();
        taskDAO = new TaskDAO(entityManager);
        userDAO = new UserDAO(entityManager);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the list of tasks.
        List<Task> tasks = taskDAO.getAllTasks();

        // Get the list of users.
        List<User> users = userDAO.getAllUsers();

        // Set tasks and users as attributes to be used in the JSP view.
        request.setAttribute("tasks", tasks);
        request.setAttribute("users", users);

        // Forward the request to the task view page (tasks.jsp).
        request.getRequestDispatcher("/WEB-INF/views/users/task.jsp").forward(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve task data from the form.
        String description = request.getParameter("description");
        int userId = Integer.parseInt(request.getParameter("user"));
        Priority priority = Priority.valueOf(request.getParameter("priority"));
        Status status = Status.valueOf(request.getParameter("status"));

        // Create a new Task object.
        Task task = new Task();
        task.setDescription(description);

        // Get the user associated with the task.
        User user = entityManager.find(User.class, userId);
        task.setUser(user);

        task.setPriority(priority);
        task.setStatus(status);

        // Create the task using the TaskDAO.
        taskDAO.createTask(task);

        // Redirect to the task list page.
        response.sendRedirect(request.getContextPath() + "/tasks");
    }
}
