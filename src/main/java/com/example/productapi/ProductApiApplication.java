package com.example.productapi;

import com.example.productapi.model.Category;
import com.example.productapi.model.Product;
import com.example.productapi.repository.CategoryRepository;
import com.example.productapi.repository.ProductRepository;
import com.example.productapi.util.CsvUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class ProductApiApplication implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(ProductApiApplication.class);

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public static void main(String[] args) {
        SpringApplication.run(ProductApiApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        List<Product> products = CsvUtil.readProductsFromCsv(getClass().getClassLoader().getResource("products.csv").getFile());
        log.info("Loading products to DB. Products "+ products);
        productRepository.saveAll(products);

        List<Category> categories = CsvUtil.readCategoriesFromCsv(getClass().getClassLoader().getResource("categories.csv").getFile());
        log.info("Loading categories to DB. Categories " + categories);
        categoryRepository.saveAll(categories);

        log.info("Data load completed");
    }
}