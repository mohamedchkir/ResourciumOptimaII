package chkir.resourciumoptimaii.dao;

import chkir.resourciumoptimaii.entities.Equipment;
import chkir.resourciumoptimaii.entities.Reservation;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class ReservationDAO {
    private final EntityManager entityManager;

    public ReservationDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Reservation createReservation(Reservation reservation) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(reservation);
            entityManager.getTransaction().commit();
            return reservation;
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            e.printStackTrace();
            return null;
        }
    }

    public List<Reservation> getAllReservations() {
        TypedQuery<Reservation> query = entityManager.createQuery("SELECT r FROM reservation r", Reservation.class);
        return query.getResultList();
    }

    public Reservation getReservationById(int reservationId) {
        return entityManager.find(Reservation.class, reservationId);
    }

    public Reservation updateReservation(Reservation reservation) {
        try {
            entityManager.getTransaction().begin();
            Reservation updatedReservation = entityManager.merge(reservation);
            entityManager.getTransaction().commit();
            return updatedReservation;
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            e.printStackTrace();
            return null;
        }
    }

    public void deleteReservation(int id) {
        entityManager.getTransaction().begin();
        Reservation reservation = entityManager.find(Reservation.class, id);
        if (reservation != null) entityManager.remove(reservation);
        entityManager.getTransaction().commit();
    }
}
