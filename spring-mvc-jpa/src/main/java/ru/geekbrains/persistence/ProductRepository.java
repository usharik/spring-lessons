package ru.geekbrains.persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.geekbrains.persistence.entity.Product;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

@Service
public class ProductRepository {

    private final EntityManagerFactory entityManagerFactory;

    @Autowired
    public ProductRepository(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    public void create(Product product) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        em.persist(product);

        em.getTransaction().commit();
        em.close();
    }

    public void update(Product product) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        em.merge(product);

        em.getTransaction().commit();
        em.close();
    }

    public List<Product> findAll() {
        EntityManager em = entityManagerFactory.createEntityManager();

        List<Product> product = em.createQuery("from Product", Product.class).getResultList();
        em.close();
        return product;
    }
}
