package chkir.resourciumoptimaii.web;

import chkir.resourciumoptimaii.dao.EquipmentDAO;
import chkir.resourciumoptimaii.dao.ReservationDAO;
import chkir.resourciumoptimaii.entities.Equipment;
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
import jakarta.servlet.http.HttpSession;

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
    private EquipmentDAO equipmentDAO;

    @Override
    public void init() throws ServletException {
        entityManagerFactory = Persistence.createEntityManagerFactory("resourceOptima");
        entityManager = entityManagerFactory.createEntityManager();
        reservationDAO = new ReservationDAO(entityManager);
        equipmentDAO = new EquipmentDAO(entityManager);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve the user ID from the session.
            HttpSession session = request.getSession(false); // Do not create a new session if it doesn't exist.
            User user = (User) request.getSession().getAttribute("user");
        if (session != null) {
            Long userId = user.getId();

            List<Equipment> equipments = equipmentDAO.getAvailableEquipment();
            List<Reservation> allReservations = reservationDAO.getAllReservations();
            request.setAttribute("reservations", allReservations);
            request.setAttribute("equipments", equipments);
            request.setAttribute("userId", userId);


            // Forward the request to the reservation view page (reservation.jsp).
            request.getRequestDispatcher("/WEB-INF/views/users/reservation.jsp").forward(request, response);
        } else {
            // Handle the case when there's no logged-in user (e.g., redirect to a login page).
            response.sendRedirect(request.getContextPath() + "/login");
        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String startDateStr = request.getParameter("start_date");
        String endDateStr = request.getParameter("end_date");
        int userId = Integer.parseInt(request.getParameter("user"));
        int equipmentId = Integer.parseInt(request.getParameter("equipment"));

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        try {
            Date startDate = dateFormat.parse(startDateStr);
            Date endDate = dateFormat.parse(endDateStr);

            User user = entityManager.find(User.class, userId);
            Equipment equipment = entityManager.find(Equipment.class,equipmentId);


            Reservation reservation = new Reservation();
            reservation.setStart_date(startDate);
            reservation.setEnd_date(endDate);
            reservation.setUser(user);
            reservation.setEquipment(equipment);

            reservationDAO.createReservation(reservation);

            response.sendRedirect(request.getContextPath() + "/reservations");
        } catch (ParseException e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/reservations?error=date");
        }
    }
}
