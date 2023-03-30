package com.pvkhai.gearpandabackend.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import jakarta.persistence.Id;

@Entity
@Getter
@Setter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String code;
    private String type;
    private String name;
    private String brand;
    private String illustration;
    private String description;
    private Long price;
    private Long quantity;

    public Product() {

    }

    public Product(String code, String type, String name, String brand, String illustration, String description, Long price, Long quantity) {
        super();
        this.code = code;
        this.type = type;
        this.name = name;
        this.brand = brand;
        this.illustration = illustration;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
    }
    
}
