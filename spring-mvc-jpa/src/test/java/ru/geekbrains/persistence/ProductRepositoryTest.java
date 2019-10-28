package ru.geekbrains.persistence;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import ru.geekbrains.controller.repr.ProductRepr;
import ru.geekbrains.persistence.entity.Category;
import ru.geekbrains.persistence.entity.Product;

import java.math.BigDecimal;
import java.util.List;
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

    @Test
    @Transactional
    public void testProductPagination() {
        Category category = new Category(null, "TestCategory", "Desc");
        category = categoryRepository.save(category);

        Product product = new Product("TestProduct1", "Desc", new BigDecimal(1001));
        product.setCategory(category);
        product = productRepository.save(product);
        product = new Product("TestProduct2", "Desc", new BigDecimal(1001));
        product.setCategory(category);
        product = productRepository.save(product);
        product = new Product("TestProduct3", "Desc", new BigDecimal(1001));
        product.setCategory(category);
        product = productRepository.save(product);

        System.out.println("------------------- Page 0");
        List<Product> page = productRepository.getAllByCategory_Id(category.getId(), PageRequest.of(0, 2));
        page.forEach(p -> System.out.println(p.getName()));

        System.out.println("------------------- Page 1");
        page = productRepository.getAllByCategory_Id(category.getId(), PageRequest.of(1, 2));
        page.forEach(p -> System.out.println(p.getName()));

    }
}
