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

    public void deleteEquipment(Long id) {
        equipmentDAO.deleteEquipment(id);
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

    public Equipment updateEquipment(Equipment equipment) {
        return equipmentDAO.updateEquipment(equipment);
    }

    public Equipment getEquipmentById(Long equipmentId) {
        return equipmentDAO.getEquipmentById(equipmentId);
    }

    public List<Equipment> getAvailableEquipment() {
        return equipmentDAO.getAvailableEquipment();
    }
}
