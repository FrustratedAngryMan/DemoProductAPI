package com.example.productapi.service;

import com.example.productapi.model.Product;
import com.example.productapi.repository.ProductRepository;
import com.example.productapi.util.CsvUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private static final String CSV_FILE = "products.csv";

    @Autowired
    private ProductRepository repository;

    public List<Product> getAllProducts() {
        return repository.findAll();
    }

    public Optional<Product> getProduct(Long id) {
        return repository.findById(id);
    }

    public Product createProduct(Product product) {
        Product saved = repository.save(product);
        saveToCsv();
        return saved;
    }

    public Product updateProduct(Long id, Product product) {
        product.setId(id);
        Product saved = repository.save(product);
        saveToCsv();
        return saved;
    }

    public void deleteProduct(Long id) {
        repository.deleteById(id);
        saveToCsv();
    }

    private void saveToCsv() {
        List<Product> allProducts = repository.findAll();
        CsvUtil.writeProductsToCsv(CSV_FILE, allProducts);
    }
}