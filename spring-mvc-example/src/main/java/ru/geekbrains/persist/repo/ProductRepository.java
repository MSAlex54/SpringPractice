package ru.geekbrains.persist.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.persist.entity.Product;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findAllByTitle(String tittle);
    List<Product> findAllByPriceBetween(BigDecimal minPrice, BigDecimal maxPrice);
    List<Product> findAllByPriceBefore(BigDecimal maxPrice);
    List<Product> findAllByPriceAfter(BigDecimal minPrice);

    List<Product> findAllByTitleAndPriceBetween(String title, BigDecimal minPrice, BigDecimal maxPrice);
    List<Product> findAllByTitleAndPriceAfter(String title, BigDecimal minPrice);
    List<Product> findAllByTitleAndPriceBefore(String title, BigDecimal maxPrice);

}
