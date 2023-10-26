package chkir.resourciumoptimaii.dao;

import chkir.resourciumoptimaii.entities.Equipment;
import chkir.resourciumoptimaii.entities.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class EquipmentDAO {

    private   EntityManager entityManager;

    public EquipmentDAO(EntityManager entityManager) {

        this.entityManager = entityManager;
    }

    public List<Equipment> getAllEquipments() {
        TypedQuery<Equipment> query = entityManager.createQuery("SELECT e FROM equipment e", Equipment.class);
        return query.getResultList();
    }

    public void deleteEquipment(Long id) {
        entityManager.getTransaction().begin();
        Equipment equipment = entityManager.find(Equipment.class, id);
        if (equipment != null) entityManager.remove(equipment);
        entityManager.getTransaction().commit();
    }

}
