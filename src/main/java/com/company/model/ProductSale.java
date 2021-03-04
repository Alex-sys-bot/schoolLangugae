package com.company.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class ProductSale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "SaleDate")
    private Date saleDate;

    @Column(name = "Quantity")
    private int quantity;

    @ManyToOne(cascade = CascadeType.ALL)
    private Product products ;

    @Override
    public String toString() {
        return "ProductSale{" +
                "id=" + id +
                ", saleDate=" + saleDate +
                ", quantity=" + quantity +
                ", product=" + products +
                '}';
    }
}
