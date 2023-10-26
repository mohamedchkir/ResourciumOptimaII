package chkir.resourciumoptimaii.dao;

import chkir.resourciumoptimaii.entities.Task;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;

public class TaskDAO {

    private EntityManager entityManager;

    public TaskDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Task> getAllTasks() {
        TypedQuery<Task> query = entityManager.createQuery("SELECT t FROM Task t", Task.class);
        return query.getResultList();
    }

    public Task getTaskById(int taskId) {
        return entityManager.find(Task.class, taskId);
    }

    public Task createTask(Task task) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(task);
            entityManager.getTransaction().commit();
            return task;
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            e.printStackTrace();
            return null;
        }
    }

    public Task updateTask(Task task) {
        try {
            entityManager.getTransaction().begin();
            task = entityManager.merge(task);
            entityManager.getTransaction().commit();
            return task;
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            e.printStackTrace();
            return null;
        }
    }

    public void deleteTask(int id) {
        entityManager.getTransaction().begin();
        Task task = entityManager.find(Task.class, id);
        if (task != null) entityManager.remove(task);
        entityManager.getTransaction().commit();
    }
}
