package chkir.resourciumoptimaii.web;

import chkir.resourciumoptimaii.dao.EquipmentDAO;

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

@WebServlet(name ="DeleteEquipmentServlet" , value = "/deleteEquipment")
public class DeleteEquipmentServlet extends HttpServlet {
    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;
    private EquipmentService equipmentService;


    public void init() throws ServletException {

        entityManagerFactory = Persistence.createEntityManagerFactory("resourceOptima");
        entityManager  = entityManagerFactory.createEntityManager();
        equipmentService = new EquipmentService(entityManager);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the user ID to be deleted from the request parameter.
        String EquipmentIdParam = request.getParameter("EquipmentId");

        if (EquipmentIdParam != null) {
            try {
                int equipmentId = Integer.parseInt(EquipmentIdParam);

                // Delete the user by ID.
                equipmentService.deleteEquipment(equipmentId);
            } catch (NumberFormatException e) {
                // Handle the case where the userId parameter is not a valid number.
                // You can add an error message or redirect to an error page.
                e.printStackTrace();
            }
        }

        // Redirect to a page after user deletion (e.g., users list or another page).
        response.sendRedirect(request.getContextPath() + "/equipments");
    }
}
