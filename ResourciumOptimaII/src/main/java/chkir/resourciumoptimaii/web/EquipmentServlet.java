package chkir.resourciumoptimaii.web;

import chkir.resourciumoptimaii.dao.EquipmentDAO;
import chkir.resourciumoptimaii.dao.UserDAO;
import chkir.resourciumoptimaii.entities.Equipment;
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
import java.util.List;

@WebServlet(name = "EquipmentServlet", value = "/equipments")
public class EquipmentServlet extends HttpServlet {



    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;
    private EquipmentDAO equipmentDAO;

    @Override
    public void init() throws ServletException {

        entityManagerFactory = Persistence.createEntityManagerFactory("resourceOptima");
        entityManager  = entityManagerFactory.createEntityManager();
        equipmentDAO = new EquipmentDAO(entityManager);
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Equipment> allEquipments = equipmentDAO.getAllEquipments();
        request.setAttribute("equipments", allEquipments);
        request.getRequestDispatcher("/WEB-INF/views/users/equipment.jsp").forward(request, response);
    }
}
