package chkir.resourciumoptimaii.services;

import chkir.resourciumoptimaii.dao.EquipmentDAO;
import chkir.resourciumoptimaii.entities.Equipment;
import chkir.resourciumoptimaii.enums.EquipmentStatus;
import jakarta.persistence.EntityManager;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class EquipmentService {

    private EquipmentDAO equipmentDAO;

    public EquipmentService(EntityManager entityManager) {
        equipmentDAO = new EquipmentDAO(entityManager);
    }

    public List<Equipment> getAllEquipments() {
        return equipmentDAO.getAllEquipments();
    }

    public void deleteEquipment(int id) {
        Equipment equipment = equipmentDAO.getEquipmentById(id);

        if (equipment != null) {
            equipmentDAO.deleteEquipment(id);
        } else {
            // Handle the case where the equipment with the given ID is not found.
            throw new IllegalArgumentException("Equipment not found.");
        }
    }



    public void createEquipment(String name, String type, EquipmentStatus status, String date) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date buyDate = dateFormat.parse(date);

        Equipment equipment = new Equipment();
        equipment.setName(name);
        equipment.setBuy_date(buyDate);
        equipment.setType(type);
        equipment.setStatus(status);

        equipmentDAO.createEquipment(equipment);
    }


    public Equipment getEquipmentById(int equipmentId) {
        return equipmentDAO.getEquipmentById(equipmentId);
    }

    public List<Equipment> getAvailableEquipment() {
        return equipmentDAO.getAvailableEquipment();
    }

    public Equipment updateEquipment(int id, String name, String type, EquipmentStatus status, String date) throws ParseException {
        Equipment existingEquipment = equipmentDAO.getEquipmentById(id);

        if (existingEquipment == null) {
            // Handle the case where the equipment with the given ID is not found.
            return null;
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date buyDate = dateFormat.parse(date);

        existingEquipment.setName(name);
        existingEquipment.setType(type);
        existingEquipment.setStatus(status);
        existingEquipment.setBuy_date(buyDate);

        return equipmentDAO.updateEquipment(existingEquipment);
    }

}
