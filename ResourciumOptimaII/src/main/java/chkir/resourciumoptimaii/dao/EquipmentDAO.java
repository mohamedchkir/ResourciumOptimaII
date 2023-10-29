package chkir.resourciumoptimaii.dao;

import chkir.resourciumoptimaii.entities.Equipment;
import chkir.resourciumoptimaii.enums.EquipmentStatus;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;

public class EquipmentDAO {

    private EntityManager entityManager;

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

    public void createEquipment(Equipment equipment) {
        entityManager.getTransaction().begin();
        entityManager.persist(equipment);
        entityManager.getTransaction().commit();
    }

    public Equipment updateEquipment(Equipment equipment) {
        entityManager.getTransaction().begin();
        Equipment equipment1 = entityManager.merge(equipment);
        entityManager.getTransaction().commit();
        return equipment1;
    }

    public Equipment getEquipmentById(Long equipmentId) {
        entityManager.getTransaction().begin();
        Equipment equipment = entityManager.find(Equipment.class, equipmentId);
        entityManager.getTransaction().commit();
        return equipment;
    }

    public List<Equipment> getAvailableEquipment() {
        TypedQuery<Equipment> query  = entityManager.createQuery("SELECT e FROM equipment  e WHERE e.status = :status", Equipment.class);
        query.setParameter("status", EquipmentStatus.AVAILABLE);
        return query.getResultList();
    }
}
