package com.company.controller;

import com.company.dao.Dao;
import com.company.model.Product;
import com.company.service.ServiceProduct;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ControllerMainWindow {

    private final SessionFactory factory;
    ObservableList<Product> products = FXCollections.observableArrayList();


    @FXML
    private FlowPane flowPane;

    public ControllerMainWindow() {
        this.factory = new Configuration().configure().buildSessionFactory();
    }


    public void initialize(){
        iniData();
        createTilesProduct();
    }


    public void createTilesProduct(){
        for (Product product: products) {
            flowPane.setAlignment(Pos.TOP_LEFT);
            flowPane.setVgap(20);
            flowPane.setHgap(20);

//            images;
            Image image = new Image(product.getMainImagePath());
            ImageView logo = new ImageView(image);
            logo.setFitHeight(300) ;
            logo.setFitWidth(200);
            flowPane.getChildren().add(logo);
        }
    }


    public void iniData(){
        Dao<Product, Integer> daoManufacturer = new ServiceProduct(factory);
        products.addAll(daoManufacturer.readAll());
    }
}
