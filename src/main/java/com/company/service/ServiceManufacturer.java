package com.company.service;

import com.company.dao.Dao;
import com.company.model.Manufacturer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class ServiceManufacturer implements Dao<Manufacturer, Integer> {


    private final SessionFactory factory;
    public ServiceManufacturer(SessionFactory factory){
        this.factory = factory;
    }


    @Override
    public void save(Manufacturer manufacturer) {
        try (Session session = factory.openSession()){
            session.beginTransaction();
            session.save(manufacturer);
            session.getTransaction().commit();
        }
    }

    @Override
    public void update(Manufacturer manufacturer) {
        try (Session session = factory.openSession()){
            session.beginTransaction();
            session.update(manufacturer);
            session.getTransaction().commit();
        }
    }

    @Override
    public void delete(Manufacturer manufacturer) {
        try (Session session = factory.openSession()){
            session.beginTransaction();
            session.delete(manufacturer);
            session.getTransaction().commit();
        }
    }

    @Override
    public Manufacturer readById(Integer id) {
        try (Session session = factory.openSession()){
            return session.get(Manufacturer.class, id);
        }
    }

    @Override
    public List<Manufacturer> readAll() {
        try (Session session = factory.openSession()){
            Query<Manufacturer> query = session.createQuery("from Manufacturer");
            return query.list();
        }
    }
}
