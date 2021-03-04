package com.company.service;

import com.company.dao.Dao;
import com.company.model.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class ServiceProduct implements Dao<Product, Integer> {

    private final SessionFactory factory;

    public ServiceProduct(SessionFactory factory) {
        this.factory = factory;
    }


    public void save(Product product) {
        try (Session session = factory.openSession()){
            session.beginTransaction();
            session.save(product);
            session.getTransaction().commit();
        }
    }

    public void update(Product product) {
        try (Session session = factory.openSession()){
            session.beginTransaction();
            session.save(product);
            session.getTransaction().commit();
        }
    }

    public void delete(Product product) {
        try (Session session = factory.openSession()){
            session.beginTransaction();
            session.save(product);
            session.getTransaction().commit();
        }
    }

    public Product readById(Integer id) {
        try (Session session = factory.openSession()){
            return session.get(Product.class, id);
        }
    }

    @Override
    public List<Product> readAll() {
        try (Session session = factory.openSession()){
            Query<Product> query = session.createQuery("from Product");
            return query.list();
        }
    }
}
