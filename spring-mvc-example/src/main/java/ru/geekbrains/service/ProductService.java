package ru.geekbrains.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.geekbrains.persist.entity.Product;
import ru.geekbrains.persist.repo.ProductRepository;

import java.math.BigDecimal;


@Service
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
    public Page<Product> filterAll(String title, BigDecimal minPrice, BigDecimal maxPrice, Pageable pageable) {
        if (title.equals("") && minPrice.intValue() == 0 && maxPrice.intValue() == 0) {
            return repository.findAll(pageable);
        } else if (title.equals("") && minPrice.intValue() == 0 && maxPrice.intValue() != 0) {
            return repository.findAllByPriceLessThanEqual(maxPrice,pageable);
        } else if (title.equals("") && minPrice.intValue() != 0 && maxPrice.intValue() == 0) {
            return repository.findAllByPriceGreaterThanEqual(minPrice,pageable);
        } else if (title.equals("") && minPrice.intValue() != 0 && maxPrice.intValue() != 0) {
            return repository.findAllByPriceBetween(minPrice, maxPrice,pageable);
        } else if (!title.equals("") && minPrice.intValue() == 0 && maxPrice.intValue() == 0) {
            return repository.findAllByTitleContains(title,pageable);
        } else if (!title.equals("") && minPrice.intValue() == 0 && maxPrice.intValue() != 0) {
            return repository.findAllByTitleContainsAndPriceLessThanEqual(title, maxPrice,pageable);
        } else if (!title.equals("") && minPrice.intValue() != 0 && maxPrice.intValue() == 0) {
            return repository.findAllByTitleContainsAndPriceGreaterThanEqual(title, minPrice,pageable);
        } else {
            return repository.findAllByTitleContainsAndPriceBetween(title, minPrice, maxPrice,pageable);
        }
    }
    public void removeById(Long id) {
        repository.deleteById(id);
    }
    public Product getById(Long id) {
        return repository.getById(id);
    }
}
