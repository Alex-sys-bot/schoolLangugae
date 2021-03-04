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
@Table(name = "manufacturer")
public class Manufacturer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "Name")
    private String name;

    @Column(name = "LastVisitDate")
    private Date lastVisitDate;

    @OneToMany(mappedBy = "manufacturers",fetch = FetchType.EAGER)
    private Set<Product> products;


    @Override
    public String toString() {
        return "Manufacturer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastVisitDate=" + lastVisitDate +
                ", product=" + products +
                '}';
    }
}
