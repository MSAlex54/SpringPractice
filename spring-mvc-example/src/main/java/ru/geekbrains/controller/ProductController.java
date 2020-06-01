package ru.geekbrains.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.geekbrains.persist.entity.Product;
import ru.geekbrains.persist.repo.ProductRepository;

import java.math.BigDecimal;


@RequestMapping("/product")
@Controller
public class ProductController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private ProductRepository productRepository;

    @Autowired
    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping
    public String productList(Model model,
                              @RequestParam (defaultValue = "_", required=false) String title,
                              @RequestParam (defaultValue = "0", required=false) BigDecimal minPrice,
                              @RequestParam (defaultValue = "0", required=false) BigDecimal maxPrice
    ) {
        logger.info("Product list");
        if (title.equals("_") && minPrice.intValue()==0 && maxPrice.intValue()==0){
            model.addAttribute("products", productRepository.findAll());
        } else if (title.equals("_") && minPrice.intValue()==0 && maxPrice.intValue()!=0) {
            model.addAttribute("products", productRepository.findAllByPriceBefore(maxPrice));
        } else if (title.equals("_") && minPrice.intValue()!=0 && maxPrice.intValue()==0) {
            model.addAttribute("products", productRepository.findAllByPriceAfter(minPrice));
        } else if (title.equals("_") && minPrice.intValue()!=0 && maxPrice.intValue()!=0) {
            model.addAttribute("products", productRepository.findAllByPriceBetween(minPrice, maxPrice));
        } else if (!title.equals("_") && minPrice.intValue()==0 && maxPrice.intValue()==0) {
            model.addAttribute("products", productRepository.findAllByTitle(title));
        } else if (!title.equals("_") && minPrice.intValue()==0 && maxPrice.intValue()!=0) {
            model.addAttribute("products", productRepository.findAllByTitleAndPriceBefore(title, maxPrice));
        } else if (!title.equals("_") && minPrice.intValue()!=0 && maxPrice.intValue()==0) {
            model.addAttribute("products", productRepository.findAllByTitleAndPriceAfter(title, minPrice));
        } else if (!title.equals("_") && minPrice.intValue()!=0 && maxPrice.intValue()!=0) {
            model.addAttribute("products", productRepository.findAllByTitleAndPriceBetween(title, minPrice, maxPrice));
        } else {
            model.addAttribute("products", productRepository.findAll());
        }

        return "products";
    }

    @GetMapping("new")
    public String createProduct(Model model) {
        logger.info("Create user form");
        model.addAttribute("product", new Product());
        return "product";
    }

    @PostMapping
    public String saveProduct(Product product) {
        logger.info("Save user method");
        productRepository.save(product);
        return "redirect:/product";
    }
}
