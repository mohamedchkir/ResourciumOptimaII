package chkir.resourciumoptimaii.dao;

import chkir.resourciumoptimaii.entities.Role;
import chkir.resourciumoptimaii.entities.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class UserDAO {

    private EntityManager entityManager;

    public UserDAO(EntityManager entityManager) {

        this.entityManager = entityManager;
    }

    public void registerUser(User user) {
        try {
            entityManager.getTransaction().begin();

            // Fetch the role entity with ID 2 (assuming 2 represents the desired role).
            Role role = entityManager.find(Role.class, 2); // Assuming Role class and Role table exist.

            if (role != null) {
                // Set the role for the user.
                user.setRole(role);
                entityManager.persist(user);
                entityManager.getTransaction().commit();
            } else {
                // Handle the case where the role with ID 1 does not exist.
                entityManager.getTransaction().rollback();
                // Handle the error appropriately.
            }
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            e.printStackTrace();
            // Handle the user creation error here.
        }
    }

    public User authenticateUser(String email, String password) {
        try {
            // Create a query to find a user with the given email.
            TypedQuery<User> query = entityManager.createQuery("SELECT u FROM user u WHERE u.email = :email", User.class);
            query.setParameter("email", email);
            User user = query.getSingleResult();
            System.out.println(user);

            // Check if the provided password matches the stored password (use a secure password hashing method).
            if (user != null && isPasswordValid(password, user.getPassword())) {
                return (User) user; // Authentication successful
            } else {
                return null; // Authentication failed
            }
        } catch (NoResultException e) {
            return null; // User with the provided email not found
        }
    }

    // Method to check if the provided password matches the stored hashed password.
    private boolean isPasswordValid(String providedPassword, String storedPassword) {
        // Implement your password validation/hashing logic here.
        // You should use a secure hashing algorithm (e.g., BCrypt) to validate passwords securely.
        // For simplicity, this example does not cover password hashing; you should do it in a secure way.
        return providedPassword.equals(storedPassword);
    }
    public User getUserById(int userId) {
        return entityManager.find(User.class, userId);
    }

    public User getUserByEmail(String email) {
        try {
            TypedQuery<User> query = entityManager.createQuery("SELECT u FROM user u WHERE u.email = :email", User.class);
            query.setParameter("email", email);
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null; // User with the provided email not found.
        }
    }

    public List<User> getAllUsers() {
        TypedQuery<User> query = entityManager.createQuery("SELECT u FROM user u", User.class);
        return query.getResultList();
    }
}
