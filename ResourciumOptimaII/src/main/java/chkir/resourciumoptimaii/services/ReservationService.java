package chkir.resourciumoptimaii.service;

import chkir.resourciumoptimaii.dao.ReservationDAO;
import chkir.resourciumoptimaii.entities.Equipment;
import chkir.resourciumoptimaii.entities.Reservation;
import chkir.resourciumoptimaii.entities.User;
import chkir.resourciumoptimaii.enums.EquipmentStatus;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ReservationService {
    private final EntityManagerFactory entityManagerFactory;
    private final EntityManager entityManager;
    private final ReservationDAO reservationDAO;

    public ReservationService() {
        entityManagerFactory = Persistence.createEntityManagerFactory("resourceOptima");
        entityManager = entityManagerFactory.createEntityManager();
        reservationDAO = new ReservationDAO(entityManager);
    }

    public List<Reservation> getAllReservations() {
        return reservationDAO.getAllReservations();
    }

    public Reservation createReservation(String startDateStr, String endDateStr, int userId, int equipmentId)
            throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = dateFormat.parse(startDateStr);
        Date endDate = dateFormat.parse(endDateStr);

        User user = entityManager.find(User.class, userId);
        Equipment equipment = entityManager.find(Equipment.class, equipmentId);

        Reservation reservation = new Reservation();
        reservation.setStart_date(startDate);
        reservation.setEnd_date(endDate);
        reservation.setUser(user);
        reservation.setEquipment(equipment);

        // Change the status of the equipment to "in use."
        equipment.setStatus(EquipmentStatus.IN_USE);
        entityManager.merge(equipment);

        return reservationDAO.createReservation(reservation);
    }
}
