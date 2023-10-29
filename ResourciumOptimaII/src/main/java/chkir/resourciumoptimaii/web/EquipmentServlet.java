package chkir.resourciumoptimaii.web;

import chkir.resourciumoptimaii.dao.EquipmentDAO;
import chkir.resourciumoptimaii.entities.Equipment;
import chkir.resourciumoptimaii.enums.EquipmentStatus;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import chkir.resourciumoptimaii.services.EquipmentService;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet(name = "EquipmentServlet", value = "/equipments")
public class EquipmentServlet extends HttpServlet {

    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;
    private EquipmentService equipmentService;

    @Override
    public void init() throws ServletException {
        entityManagerFactory = Persistence.createEntityManagerFactory("resourceOptima");
        entityManager = entityManagerFactory.createEntityManager();
        equipmentService = new EquipmentService(entityManager);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Equipment> allEquipments = equipmentService.getAllEquipments();
        request.setAttribute("equipments", allEquipments);
        request.getRequestDispatcher("/WEB-INF/views/users/equipment.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String type = req.getParameter("type");
        EquipmentStatus status = EquipmentStatus.valueOf(req.getParameter("status"));
        String date = req.getParameter("date");

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date buyDate = null;
        try {
            buyDate = dateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
            resp.sendRedirect("/register?error=date");
            return;
        }

        try {
            equipmentService.createEquipment(name, type, status, date);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        resp.sendRedirect(req.getContextPath() + "/equipments");
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Retrieve the parameters from the request.
        Long id = Long.parseLong(req.getParameter("id"));
        String name = req.getParameter("name");
        String type = req.getParameter("type");
        EquipmentStatus status = EquipmentStatus.valueOf(req.getParameter("status"));
        String date = req.getParameter("date");

        try {
            Equipment updatedEquipment = equipmentService.updateEquipment(id, name, type, status, date);

            if (updatedEquipment != null) {
                // Update successful
                resp.sendRedirect(req.getContextPath() + "/equipments");
            } else {
                // Handle the case where the equipment with the given ID is not found.
                resp.sendRedirect(req.getContextPath() + "/equipments?error=notfound");
            }
        } catch (ParseException e) {
            // Handle parsing error
            resp.sendRedirect(req.getContextPath() + "/equipments?error=date");
        }
    }

}
