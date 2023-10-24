package chkir.resourciumoptimaii.dao;

import chkir.resourciumoptimaii.entities.Role;
import chkir.resourciumoptimaii.entities.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import org.mindrot.jbcrypt.BCrypt;

import java.util.List;

public class UserDAO {

    private EntityManager entityManager;

    public UserDAO(EntityManager entityManager) {

        this.entityManager = entityManager;
    }

    public boolean registerUser(User user) {

        try {
            entityManager.getTransaction().begin();

            // Check if the email already exists in the database.
            TypedQuery<User> emailCheckQuery = entityManager.createQuery("SELECT u FROM user  u WHERE u.email = :email", User.class);
            emailCheckQuery.setParameter("email", user.getEmail());

            if (emailCheckQuery.getResultList().isEmpty()) {
                // Fetch the role entity with ID 2 (assuming 2 represents the desired role).
                Role role = entityManager.find(Role.class, 2); // Assuming Role class and Role table exist.

                if (role != null) {
                    // Set the role for the user.
                    user.setRole(role);
                    entityManager.persist(user);
                    entityManager.getTransaction().commit();
                    return true;
                } else {
                    // Handle the case where the role with ID 2 does not exist.
                    entityManager.getTransaction().rollback();
                    // Handle the error appropriately.
                    return false;
                }
            } else {
                // Handle the case where the email already exists.
                entityManager.getTransaction().rollback();
                return false;
            }

        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            e.printStackTrace();
            // Handle the user creation error here.
            return false;
        }
    }


    public User authenticateUser(String email) {

        try {
            TypedQuery<User> query = entityManager.createQuery("SELECT u FROM user u WHERE u.email = :email", User.class);
            query.setParameter("email", email);
            User user = query.getSingleResult();

            return user;

        } catch (NoResultException e) {

            return null;
        }
    }

    // Method to check if the provided password matches the stored hashed password.
    public boolean isPasswordValid(String providedPassword, String storedPassword) {
        return BCrypt.checkpw(providedPassword,storedPassword);
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


    public User updateUser(User user) {
        entityManager.getTransaction().begin();
        User user1 = entityManager.merge(user);
        entityManager.getTransaction().commit();
        return user1;
    }

    public void deleteUser(Long id) {
        entityManager.getTransaction().begin();
        User user = entityManager.find(User.class, id);
        if (user != null) entityManager.remove(user);
        entityManager.getTransaction().commit();
    }

}
