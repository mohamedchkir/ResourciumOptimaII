package chkir.resourciumoptimaii.web;



        import jakarta.servlet.RequestDispatcher;
        import jakarta.servlet.ServletException;
        import jakarta.servlet.annotation.WebServlet;
        import jakarta.servlet.http.HttpServlet;
        import jakarta.servlet.http.HttpServletRequest;
        import jakarta.servlet.http.HttpServletResponse;

        import java.io.IOException;

@WebServlet(name = "RouteServlet", value = { "/dashboard", "/profile","/"})
public class RouteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String path = request.getServletPath();

        switch (path) {
            case "/":
                RequestDispatcher loginDispatcher = request.getRequestDispatcher("/WEB-INF/views/authentication/login.jsp");
                loginDispatcher.forward(request, response);
                break;
            case "/dashboard":
                RequestDispatcher dashboardDispatcher = request.getRequestDispatcher("/WEB-INF/views/users/users.jsp");
                dashboardDispatcher.forward(request, response);
                break;
            case "/profile":
                RequestDispatcher profileDispatcher = request.getRequestDispatcher("/WEB-INF/Dashboard/profile.jsp");
                profileDispatcher.forward(request, response);
                break;
            default:
                response.sendRedirect("/errorPage");
                break;
        }

    }

}
