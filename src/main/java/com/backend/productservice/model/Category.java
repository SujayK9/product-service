package com.backend.productservice.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


import java.util.Set;

@Entity
@Getter
@Setter
public class Category extends BaseModel{

    @Column(nullable = false)
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "category", cascade = CascadeType.REMOVE)
    @JsonManagedReference
    @JsonIgnore
    private Set<Product> products;

    // Getters and setters

    public Category(String name) {
        this.name = name;
    }

    public Category() {}
}
