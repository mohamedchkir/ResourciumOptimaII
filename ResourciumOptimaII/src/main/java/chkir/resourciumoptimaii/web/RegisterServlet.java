package chkir.resourciumoptimaii.web;


import chkir.resourciumoptimaii.dao.UserDAO;
import chkir.resourciumoptimaii.entities.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.mindrot.jbcrypt.BCrypt;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


@WebServlet(name = "RegisterServlet", value = "/register")
public class RegisterServlet extends HttpServlet {

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
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        RequestDispatcher registerDispatcher = req.getRequestDispatcher("/WEB-INF/views/authentication/register.jsp");
        registerDispatcher.forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Retrieve user registration information from the form.
        String username = req.getParameter("username");
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String email = req.getParameter("email");
        String position = req.getParameter("position");
        String hireDateString = req.getParameter("date");

        // hash the password
        String password = req.getParameter("password");
        String hashedPassword = BCrypt.hashpw(password,BCrypt.gensalt(15));

        // Parse the hire date from the form input.
        Date hireDate = null;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            hireDate = dateFormat.parse(hireDateString);
        } catch (ParseException e) {
            e.printStackTrace();

            resp.sendRedirect("/register?error=date");
            return;
        }

        // Create a new User object.
        User user = new User();
        user.setUsername(username);
        user.setName(name);
        user.setSurname(surname);
        user.setEmail(email);
        user.setPassword(hashedPassword);
        user.setPosition(position);
        user.setHireDate(hireDate);


        // Perform user registration.
        if (userDAO.registerUser(user)){
            // Redirect to a success page or login page.
            req.getSession().setAttribute("success", "Compte creer avec successé");

            String contextPath = req.getContextPath();
            String url = contextPath + "/login" ;
            resp.sendRedirect(url);

        }else {
            // Redirect to  error page or register page.
            String contextPath = req.getContextPath();
            String url = contextPath + "/register" ;
            resp.sendRedirect(url);
        }


    }
}
