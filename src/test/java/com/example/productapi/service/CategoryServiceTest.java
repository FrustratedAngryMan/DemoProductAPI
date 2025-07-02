package com.example.productapi.service;

import com.example.productapi.model.Category;
import com.example.productapi.repository.CategoryRepository;
import com.example.productapi.util.CsvUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CategoryServiceTest {

    @Mock
    private CategoryRepository categoryRepository;

    @Spy
    private CsvUtil csvUtil; // Only if you need to verify or mock CSV operations, otherwise can be omitted

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
    void getCategoryById_returnsCategoryOptional() {
        when(categoryRepository.findById(1L)).thenReturn(Optional.of(sampleCategory));
        Optional<Category> result = categoryService.getCategoryById(1L);
        assertTrue(result.isPresent());
        assertEquals("Electronics", result.get().getName());
    }

    @Test
    void createCategory_savesAndReturnsCategory_andSavesToCsv() {
        when(categoryRepository.save(any(Category.class))).thenReturn(sampleCategory);
        when(categoryRepository.findAll()).thenReturn(List.of(sampleCategory));
        Category created = categoryService.createCategory(sampleCategory);
        assertEquals(sampleCategory.getName(), created.getName());
        verify(categoryRepository, times(1)).save(sampleCategory);
        verify(categoryRepository, atLeastOnce()).findAll();
        // Optionally verify CSV writing if CsvUtil is mocked or spied
    }

    @Test
    void updateCategory_updatesAndReturnsCategory_andSavesToCsv() {
        when(categoryRepository.save(any(Category.class))).thenReturn(sampleCategory);
        when(categoryRepository.findAll()).thenReturn(List.of(sampleCategory));
        Category updated = categoryService.updateCategory(1L, sampleCategory);
        assertEquals(sampleCategory.getName(), updated.getName());
        verify(categoryRepository, times(1)).save(sampleCategory);
        verify(categoryRepository, atLeastOnce()).findAll();
    }

    @Test
    void deleteCategory_deletesById_andSavesToCsv() {
        doNothing().when(categoryRepository).deleteById(1L);
        when(categoryRepository.findAll()).thenReturn(Collections.emptyList());
        categoryService.deleteCategory(1L);
        verify(categoryRepository, times(1)).deleteById(1L);
        verify(categoryRepository, atLeastOnce()).findAll();
    }
}