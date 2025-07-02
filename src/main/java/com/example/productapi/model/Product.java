package com.example.productapi.model;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    private Long id;
    private String name;
    private BigDecimal price;
    private String description;
    private String image;
    @ElementCollection
    private List<String> socialMediaLinks;
    @ElementCollection
    private List<String> highlights;
    private String manufacturerInfo;
    private String technicalSpecs;
}