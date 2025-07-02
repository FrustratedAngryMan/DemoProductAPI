package com.example.productapi.service;

import com.example.productapi.model.Product;
import com.example.productapi.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import java.math.BigDecimal;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    private Product sampleProduct;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        sampleProduct = new Product(1L, "Phone", new BigDecimal("99.99"), "desc", "img",
                List.of("link1"), List.of("highlight"), "manufacturer", "specs");
    }

    @Test
    void getAllProducts_returnsProductList() {
        when(productRepository.findAll()).thenReturn(List.of(sampleProduct));
        List<Product> products = productService.getAllProducts();
        assertEquals(1, products.size());
        assertEquals("Phone", products.get(0).getName());
    }

    @Test
    void getProduct_returnsProductOptional() {
        when(productRepository.findById(1L)).thenReturn(Optional.of(sampleProduct));
        Optional<Product> product = productService.getProduct(1L);
        assertTrue(product.isPresent());
        assertEquals("Phone", product.get().getName());
    }

    @Test
    void createProduct_savesAndReturnsProduct() {
        when(productRepository.save(any(Product.class))).thenReturn(sampleProduct);
        Product created = productService.createProduct(sampleProduct);
        assertEquals(sampleProduct.getName(), created.getName());
        verify(productRepository, times(1)).save(sampleProduct);
    }

    @Test
    void updateProduct_updatesAndReturnsProduct() {
        when(productRepository.save(any(Product.class))).thenReturn(sampleProduct);
        Product updated = productService.updateProduct(1L, sampleProduct);
        assertEquals(sampleProduct.getName(), updated.getName());
        verify(productRepository, times(1)).save(sampleProduct);
    }

    @Test
    void deleteProduct_deletesById() {
        doNothing().when(productRepository).deleteById(1L);
        productService.deleteProduct(1L);
        verify(productRepository, times(1)).deleteById(1L);
    }
}