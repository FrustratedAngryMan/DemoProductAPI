package com.example.productapi.model;

import jakarta.persistence.*;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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