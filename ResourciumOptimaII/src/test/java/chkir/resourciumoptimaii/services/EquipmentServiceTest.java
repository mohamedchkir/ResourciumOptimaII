package chkir.resourciumoptimaii.services;

import chkir.resourciumoptimaii.dao.EquipmentDAO;
import chkir.resourciumoptimaii.entities.Equipment;
import chkir.resourciumoptimaii.enums.EquipmentStatus;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceUnit;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class EquipmentServiceTest {
    private EquipmentService equipmentService;
    private EquipmentDAO equipmentDAO;

    @PersistenceUnit
    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;

    @BeforeEach
    void setUp() {
        entityManagerFactory = Persistence.createEntityManagerFactory("resourceOptima");
        entityManager = entityManagerFactory.createEntityManager();

        equipmentDAO = new EquipmentDAO(entityManager);
        equipmentService = new EquipmentService(entityManager);
    }

    @AfterEach
    void tearDown() {
        entityManager.clear();
        entityManager.close();
        entityManagerFactory.close();
    }


    @Test
    void testGetAllEquipments() {
        // Get the initial count of equipment entries in the database
        long initialCount = equipmentDAO.getAllEquipments().size();

        // Create two new equipment entries
        Equipment equipment1 = new Equipment("Equipment1", "Type1", EquipmentStatus.AVAILABLE, new Date());
        Equipment equipment2 = new Equipment("Equipment2", "Type2", EquipmentStatus.IN_USE, new Date());

        entityManager.getTransaction().begin();
        entityManager.persist(equipment1);
        entityManager.persist(equipment2);
        entityManager.getTransaction().commit();

        // Get the updated count of equipment entries
        long updatedCount = equipmentDAO.getAllEquipments().size();

        // Assert that the updated count is equal to the initial count plus 2
        assertEquals(initialCount + 2, updatedCount);
    }


    @Test
    void testDeleteEquipment() {
        // Prepare test data
        Equipment equipment = new Equipment("Equipment1", "Type1", EquipmentStatus.AVAILABLE, new Date());
        entityManager.getTransaction().begin();
        entityManager.persist(equipment);
        entityManager.getTransaction().commit();

        // Test the service method
        equipmentService.deleteEquipment(equipment.getId());

        // Verify that equipment is deleted
        Equipment deletedEquipment = entityManager.find(Equipment.class, equipment.getId());
        assertNull(deletedEquipment);
    }

    @Test
    void testCreateEquipment() throws ParseException {
        // Get the initial number of equipment records
        List<Equipment> initialEquipmentList = equipmentService.getAllEquipments();
        int initialSize = initialEquipmentList.size();

        // Test the service method
        equipmentService.createEquipment("Equipment1", "Type1", EquipmentStatus.AVAILABLE, "2023-10-28");

        // Get the updated number of equipment records
        List<Equipment> updatedEquipmentList = equipmentService.getAllEquipments();
        int updatedSize = updatedEquipmentList.size();

        // Assert that the size increased by 1
        assertEquals(initialSize + 1, updatedSize);

        Equipment createdEquipment = updatedEquipmentList.get(updatedSize - 1);
        assertEquals("Equipment1", createdEquipment.getName());
        assertEquals("Type1", createdEquipment.getType());
        assertEquals(EquipmentStatus.AVAILABLE, createdEquipment.getStatus());
    }


    @Test
    void testUpdateEquipment() throws ParseException {
        // Prepare test data
        Equipment equipment = new Equipment("Equipment1", "Type1", EquipmentStatus.AVAILABLE, new Date());
        entityManager.getTransaction().begin();
        entityManager.persist(equipment);
        entityManager.getTransaction().commit();

        // Test the service method
        equipmentService.updateEquipment(equipment.getId(), "Updated Equipment", "Type2", EquipmentStatus.IN_USE, "2023-10-28");

        // Verify that equipment is updated
        Equipment updatedEquipment = entityManager.find(Equipment.class, equipment.getId());
        assertEquals("Updated Equipment", updatedEquipment.getName());
        assertEquals("Type2", updatedEquipment.getType());
        assertEquals(EquipmentStatus.IN_USE, updatedEquipment.getStatus());
    }

    @Test
    void testGetAvailableEquipment() {

        List<Equipment> availableEquipments = equipmentService.getAvailableEquipment();
        int availableEquipmentsNumber = availableEquipments.size();

        // Create some equipment with different statuses
        Equipment availableEquipment = new Equipment("Available Equipment", "Type1", EquipmentStatus.AVAILABLE, new Date());
        entityManager.getTransaction().begin();
        entityManager.persist(availableEquipment);

        entityManager.getTransaction().commit();

        // Get available equipment
        List<Equipment> availableEquipmentList = equipmentService.getAvailableEquipment();

        // Assert that only the "Available" equipment is retrieved
        assertEquals(availableEquipmentsNumber + 1, availableEquipmentList.size());
        assertEquals("Available Equipment", availableEquipmentList.get(0).getName());
    }

    @Test
    void testUpdateEquipmentNonExistent() throws ParseException {
        // Test updating a non-existent equipment
        Equipment updatedEquipment = equipmentService.updateEquipment(999, "Updated Equipment", "Type2", EquipmentStatus.IN_USE, "2023-10-28");

        // Assert that updatedEquipment is null
        assertNull(updatedEquipment);
    }

    @Test
    void testDeleteEquipmentNonExistent() {
        // Test deleting a non-existent equipment
        assertThrows(IllegalArgumentException.class, () -> equipmentService.deleteEquipment(999));
        // The test is expected to throw an IllegalArgumentException
    }




}
