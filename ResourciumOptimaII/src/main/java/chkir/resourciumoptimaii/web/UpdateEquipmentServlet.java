package chkir.resourciumoptimaii.web;

import chkir.resourciumoptimaii.entities.Equipment;
import chkir.resourciumoptimaii.enums.EquipmentStatus;
import chkir.resourciumoptimaii.services.EquipmentService;
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

@WebServlet(name = "UpdateEquipmentServlet", value = "/updateEquipment")
public class UpdateEquipmentServlet extends HttpServlet {

    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;
    private EquipmentService equipmentService;
    @Override
    public void init() throws ServletException {
        entityManagerFactory = Persistence.createEntityManagerFactory("resourceOptima");
        entityManager = entityManagerFactory.createEntityManager();
        equipmentService = new EquipmentService(entityManager);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve equipment details from the request
        Long id = Long.parseLong(request.getParameter("id"));
        String name = request.getParameter("name");
        EquipmentStatus status = EquipmentStatus.valueOf(request.getParameter("status"));
        String type = request.getParameter("type");
        String date = request.getParameter("date");

        try {
            // Update the equipment in the database
            equipmentService.updateEquipment(id, name, type, status, date);

            // Redirect to the equipment list page
            response.sendRedirect(request.getContextPath() + "/equipments");
        } catch (ParseException e) {
            e.printStackTrace();
            // Handle the error (e.g., display an error message)
            response.sendRedirect(request.getContextPath() + "/equipments?error=update");
        }
    }

}
