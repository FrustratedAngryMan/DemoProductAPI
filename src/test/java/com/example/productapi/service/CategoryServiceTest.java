package com.example.productapi.service;

import com.example.productapi.model.Category;
import com.example.productapi.repository.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CategoryServiceTest {

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryService categoryService;

    private Category sampleCategory;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        sampleCategory = new Category(1L, "Electronics", "Devices");
    }

    @Test
    void getAllCategories_returnsCategoryList() {
        when(categoryRepository.findAll()).thenReturn(List.of(sampleCategory));
        List<Category> categories = categoryService.getAllCategories();
        assertEquals(1, categories.size());
        assertEquals("Electronics", categories.get(0).getName());
    }

    @Test
    void getCategoryById_returnsCategory() {
        when(categoryRepository.findById(1L)).thenReturn(Optional.ofNullable(sampleCategory));
        Optional<Category> categoryOptional = categoryService.getCategoryById(1L);
        assertNotNull(categoryOptional.get());
        assertEquals("Electronics", categoryOptional.get().getName());
    }
}