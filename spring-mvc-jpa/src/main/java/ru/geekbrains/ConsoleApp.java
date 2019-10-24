package ru.geekbrains;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.geekbrains.persistence.CategoryRepository;
import ru.geekbrains.persistence.ProductRepository;

public class ConsoleApp {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext appContext = new AnnotationConfigApplicationContext(PersistenceConfig.class);

        CategoryRepository categoryRepository = (CategoryRepository) appContext.getBean("categoryRepository");
        ProductRepository productRepository = (ProductRepository) appContext.getBean("productRepository");

        categoryRepository
                .findAll()
                .forEach(c -> System.out.printf("%d\t%s%n", c.getId(), c.getName()));
        appContext.close();
    }
}
