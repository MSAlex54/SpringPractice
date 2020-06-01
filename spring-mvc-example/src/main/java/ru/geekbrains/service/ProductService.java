package ru.geekbrains.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import ru.geekbrains.persist.entity.Product;
import ru.geekbrains.persist.repo.ProductRepository;

import java.math.BigDecimal;
import java.util.List;

public class ProductService {

    private ProductRepository repository;

    @Autowired
    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public void save(Product product) {
        repository.save(product);
    }

    @Transactional(readOnly = true)
    public List<Product> findAll() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Product> findAllByTitle(String title) {
        return repository.findAllByTitle(title);
    }

    @Transactional(readOnly = true)
    public List<Product> findAllByPriceBetween(BigDecimal minPrice, BigDecimal maxPrice) {
        return repository.findAllByPriceBetween(minPrice, maxPrice);
    }

    @Transactional(readOnly = true)
    public List<Product> findAllByPriceBefore(BigDecimal maxPrice) {
        return repository.findAllByPriceBefore(maxPrice);
    }

    @Transactional(readOnly = true)
    public List<Product> findAllByPriceAfter(BigDecimal minPrice) {
        return repository.findAllByPriceAfter(minPrice);
    }

    @Transactional(readOnly = true)
    public List<Product> findAllByTitleAndPriceBetween(String title, BigDecimal minPrice, BigDecimal maxPrice) {
        return repository.findAllByTitleAndPriceBetween(title, minPrice, maxPrice);
    }

    @Transactional(readOnly = true)
    public List<Product> findAllByTitleAndPriceAfter(String title, BigDecimal minPrice) {
        return repository.findAllByTitleAndPriceAfter(title, minPrice);
    }

    @Transactional(readOnly = true)
    public List<Product> findAllByTitleAndPriceBefore(String title, BigDecimal maxPrice){
        return repository.findAllByTitleAndPriceBefore(title, maxPrice);
    }

}
