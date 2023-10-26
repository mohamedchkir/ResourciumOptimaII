package chkir.resourciumoptimaii.web;

import chkir.resourciumoptimaii.dao.EquipmentDAO;
import chkir.resourciumoptimaii.entities.Equipment;
import chkir.resourciumoptimaii.entities.User;
import chkir.resourciumoptimaii.enums.EquipmentStatus;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String name = req.getParameter("name");
        String type = req.getParameter("type");
        EquipmentStatus status = EquipmentStatus.valueOf(req.getParameter("status"));
        String date = req.getParameter("date");

        // Parse the hire date from the form input.
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date buy_Date = null;
        try {
             buy_Date = dateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();

            resp.sendRedirect("/register?error=date");
            return;
        }

        // Create a new User object.
        Equipment equipment = new Equipment();

        equipment.setName(name);
        equipment.setBuy_date(buy_Date);
        equipment.setType(type);
        equipment.setStatus(status);
        equipmentDAO.createEquipment(equipment);

        resp.sendRedirect(req.getContextPath() + "/equipments");
    }
}
