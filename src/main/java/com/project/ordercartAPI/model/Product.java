package com.project.ordercartAPI.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Builder;

@Builder
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String unitValue;
    @Builder.Default
    private Boolean available = true;
    @ManyToOne
    @JsonIgnore
    private Restaurant restaurant;
}
