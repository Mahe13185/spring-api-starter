package com.codewithmosh.store.repositories;

import com.codewithmosh.store.entities.Product;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @EntityGraph(attributePaths = "category")
    List<Product> findAllByCategoryId(Byte categoryId);

    @EntityGraph(attributePaths = "category")
    @Query("select p from Product p")
    List<Product> findAllByCategoryId();

    Optional<Product> findProductById(Long id);
}