package com.project.ordercartAPI.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.ordercartAPI.enumeration.PaymentMethod;
import jakarta.persistence.*;

import java.util.List;

public class OrderCart {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JsonIgnore
    private Client client;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Iten> itens;
    private Double totalÁmount;
    @Enumerated
    private PaymentMethod paymentMethod;
    private boolean closed;

}
