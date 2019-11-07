package ru.geekbrains.persistence;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.jdbc.Sql;
import ru.geekbrains.persistence.entity.Category;
import ru.geekbrains.persistence.entity.Product;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.util.List;

import static ru.geekbrains.persistence.ProductRepository.category;
import static ru.geekbrains.persistence.ProductRepository.priceFrom;

@DataJpaTest
@Sql(scripts = "classpath:test_data.sql")
public class RepositoryTest {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void testCriteriaApi() {
        CriteriaBuilder cb = em.getCriteriaBuilder();

        CriteriaQuery<Product> query = cb.createQuery(Product.class);

        Root<Product> from = query.from(Product.class);
        from.fetch("category", JoinType.LEFT);

        query.where(cb.ge(from.get("id"), 1));

        List<Product> products = em.createQuery(query).getResultList();
        products.forEach(p -> System.out.println(p.getName()));
    }

    @Test
    public void testSpecification() {
        Specification<Product> spec = Specification.where(null);

        spec = spec
                .and(category(new Category(1L)))
                .and(priceFrom(new BigDecimal(10)))
                .and(null);

        Page<Product> products = productRepository.findAll(spec, PageRequest.of(0, 3));
    }
}
