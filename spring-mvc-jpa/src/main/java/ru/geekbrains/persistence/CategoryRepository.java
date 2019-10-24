package ru.geekbrains.persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.geekbrains.persistence.entity.Category;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

@Service
public class CategoryRepository {

    private final EntityManagerFactory entityManagerFactory;

    @Autowired
    public CategoryRepository(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    public void create(Category category) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        em.persist(category);

        em.getTransaction().commit();
        em.close();
    }

    public void update(Category category) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        em.merge(category);

        em.getTransaction().commit();
        em.close();
    }

    public List<Category> findAll() {
        EntityManager em = entityManagerFactory.createEntityManager();

        List<Category> categories = em.createQuery("from Category", Category.class).getResultList();
        em.close();
        return categories;
    }

    public Category findById(Long id) {
        EntityManager em = entityManagerFactory.createEntityManager();

        Category category = em.find(Category.class, id);
        em.close();
        return category;
    }
}
