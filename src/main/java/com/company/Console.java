package com.company;

import com.company.dao.Dao;
import com.company.model.Product;
import com.company.service.ServiceProduct;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.List;


public class Console {
    public static void main(String[] arg) {
        SessionFactory factory = new Configuration().configure().buildSessionFactory();

        Dao<Product, Integer> daoProd = new ServiceProduct(factory);

        List<Product> products = new ArrayList<>();
        products.addAll(daoProd.readAll());
        for (Product product: products) {
            System.out.println(product.getMainImagePath());
        }

        factory.close();
    }
}
