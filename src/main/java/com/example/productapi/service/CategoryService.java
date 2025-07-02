package com.example.productapi.service;

import com.example.productapi.model.Category;
import com.example.productapi.repository.CategoryRepository;
import com.example.productapi.util.CsvUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private static final String CSV_FILE = "categories.csv";

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Optional<Category> getCategoryById(Long id) {
        return categoryRepository.findById(id);
    }

    public Category createCategory(Category category) {
        Category saved = categoryRepository.save(category);
        saveToCsv();
        return saved;
    }

    public Category updateCategory(Long id, Category category) {
        category.setId(id);
        Category saved = categoryRepository.save(category);
        saveToCsv();
        return saved;
    }

    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
        saveToCsv();
    }

    private void saveToCsv() {
        List<Category> allCategories = categoryRepository.findAll();
        CsvUtil.writeCategoriesToCsv(CSV_FILE, allCategories);
    }
}