package chkir.resourciumoptimaii.web;

import chkir.resourciumoptimaii.dao.ReservationDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "DeleteReservationServlet", value = "/deleteReservation")
public class DeleteReservationServlet extends HttpServlet {

    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;
    private ReservationDAO reservationDAO;

    @Override
    public void init() throws ServletException {
        entityManagerFactory = Persistence.createEntityManagerFactory("resourceOptima");
        entityManager = entityManagerFactory.createEntityManager();
        reservationDAO = new ReservationDAO(entityManager);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the reservation ID to be deleted from the request parameter.
        String reservationIdParam = request.getParameter("reservationId");

        if (reservationIdParam != null) {
            try {
                    int reservationId = Integer.parseInt(reservationIdParam);

                // Delete the reservation by ID.
                reservationDAO.deleteReservation(reservationId);
            } catch (NumberFormatException e) {
                // Handle the case where the reservationId parameter is not a valid number.
                // You can add an error message or redirect to an error page.
                e.printStackTrace();
            }
        }

        // Redirect to a page after reservation deletion (e.g., reservations list or another page).
        response.sendRedirect(request.getContextPath() + "/reservations");
    }
}
