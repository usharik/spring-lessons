package ru.geekbrains.persistence;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import ru.geekbrains.controller.repr.ProductRepr;
import ru.geekbrains.persistence.entity.Category;
import ru.geekbrains.persistence.entity.Product;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TestPersistenceConfig.class)
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    @Transactional
    public void testProductReprProjection() {
        Category category = new Category(null, "TestCategory", "Desc");
        category = categoryRepository.save(category);

        Product product = new Product("TestProduct", "Desc", new BigDecimal(1001));
        product.setCategory(category);
        product = productRepository.save(product);

        Optional<ProductRepr> optProductRepr = productRepository.getProductReprById(product.getId());
        assertTrue(optProductRepr.isPresent());
        assertEquals("TestProduct", optProductRepr.get().getName());
        assertEquals(category.getId(), optProductRepr.get().getCategoryId());
        assertEquals(category.getName(), optProductRepr.get().getCategoryName());
    }
}
