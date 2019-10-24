package ru.geekbrains.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.geekbrains.persistence.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
