package ru.geekbrains.persistence;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import ru.geekbrains.PersistenceConfig;
import ru.geekbrains.controller.repr.ProductRepr;

import java.util.Optional;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {PersistenceConfig.class})
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void testProductReprProjection() {
        Optional<ProductRepr> optProductRepr = productRepository.getProductReprById(3L);
        assertTrue(optProductRepr.isPresent());
    }
}
