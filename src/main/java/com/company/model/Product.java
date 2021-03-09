package com.company.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "Title")
    private String title;

    @Column(name = "Cost")
    private double cost;

    @Column(name = "Description")
    private String description;

    @Column(name = "MainImagePath")
    private String mainImagePath;

    @Column(name = "IsActive")
    private String isActive;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ManufacturerID")
    private Manufacturer manufacturers;

    @OneToMany(mappedBy = "products", fetch = FetchType.EAGER)
    Set<ProductSale> productSales;


    @Override
    public String toString() {
        return "Product{" +
                ", isActive='" + isActive + '\'' +
                '}';
    }
}
