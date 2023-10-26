package chkir.resourciumoptimaii.web;

import chkir.resourciumoptimaii.dao.ReservationDAO;
import chkir.resourciumoptimaii.entities.Reservation;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet(name = "ReservationServlet", value = "/reservations")
public class ReservationServlet extends HttpServlet {
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
        List<Reservation> allReservations = reservationDAO.getAllReservations();
        request.setAttribute("reservations", allReservations);
        request.getRequestDispatcher("/WEB-INF/views/users/reservation.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String startDateStr = request.getParameter("start_date");
        String endDateStr = request.getParameter("end_date");
        int userId = Integer.parseInt(request.getParameter("user_id"));
        int equipmentId = Integer.parseInt(request.getParameter("equipment_id"));

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        try {
            Date startDate = dateFormat.parse(startDateStr);
            Date endDate = dateFormat.parse(endDateStr);

            User user = entityManager.find(User.class, userId);

            Reservation reservation = new Reservation();
            reservation.setStart_date(startDate);
            reservation.setEnd_date(endDate);
            reservation.setUser(user);

            // You can also set the equipment for the reservation if needed.

            reservationDAO.createReservation(reservation);

            response.sendRedirect(request.getContextPath() + "/reservations");
        } catch (ParseException e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/reservations?error=date");
        }
    }
}
