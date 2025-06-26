package app;

import jakarta.persistence.*;
import java.util.List;

public class StudentDaoImpl implements GenericDao<Student, Long> {
    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("hillel-persistence-unit");

    public void save(Student student) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(student);
        em.getTransaction().commit();
        em.close();
    }

    public Student findById(Long id) {
        EntityManager em = emf.createEntityManager();
        Student s = em.find(Student.class, id);
        em.close();
        return s;
    }

    public Student findByEmail(String email) {
        EntityManager em = emf.createEntityManager();
        Student s = em.createQuery("SELECT s FROM Student s WHERE s.email = :email", Student.class)
                .setParameter("email", email)
                .getSingleResult();
        em.close();
        return s;
    }

    public List<Student> findAll() {
        EntityManager em = emf.createEntityManager();
        List<Student> result = em.createQuery("FROM Student", Student.class).getResultList();
        em.close();
        return result;
    }

    public Student update(Student student) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Student merged = em.merge(student);
        em.getTransaction().commit();
        em.close();
        return merged;
    }

    public boolean deleteById(Long id) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Student s = em.find(Student.class, id);
        if (s != null) em.remove(s);
        em.getTransaction().commit();
        em.close();
        return s != null;
    }
}
