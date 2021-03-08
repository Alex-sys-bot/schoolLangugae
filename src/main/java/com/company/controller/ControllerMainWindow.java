package com.company.controller;

import com.company.dao.Dao;
import com.company.model.Product;
import com.company.service.ServiceProduct;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ControllerMainWindow {

    ObservableList<Product> products = FXCollections.observableArrayList();

//    Session;
    private final SessionFactory factory;
    public ControllerMainWindow() {
        this.factory = new Configuration().configure().buildSessionFactory();
    }

    @FXML
    private FlowPane flowPane;
    @FXML
    private TextField txtSearch;



    public void initialize(){
//        initialize from dataBase;
        initData(products);

//        search by named book;
        searchBook();
    }

    private void initData(ObservableList<Product> list){
        Dao<Product, Integer> daoManufacturer = new ServiceProduct(factory);
        products.addAll(daoManufacturer.readAll());
    }

    private void createTilesProduct(){
        flowPane.setHgap(25);
        flowPane.setVgap(15);
        flowPane.setAlignment(Pos.CENTER_LEFT);
        flowPane.setOrientation(Orientation.HORIZONTAL);
        flowPane.setPrefWidth(1550);


        for (Product product: products) {
//            Contain cover;
            AnchorPane anchorPane = new AnchorPane();

//            image;
            ImageView imageView = new ImageView();
            imageView.setImage(new Image(product.getMainImagePath()));
            imageView.setFitHeight(300);
            imageView.setFitWidth(200);

//            label named and cost;
            Label labelNamed = new Label();
            labelNamed.setText(product.getTitle());
            labelNamed.setPrefHeight(320);
            labelNamed.setPadding(new Insets(300,0,0,0));
            labelNamed.setWrapText(true);
            labelNamed.setMaxWidth(200);

            Label labelCost = new Label();
            labelCost.setText("Стоимость: " + product.getCost() + " " + "руб.");
            labelCost.setPrefHeight(320);
            labelCost.setPadding(new Insets(315,0,0,0));

//            Node;
            anchorPane.getChildren().add(imageView);
            anchorPane.getChildren().add(labelNamed);
            anchorPane.getChildren().add(labelCost);
            flowPane.getChildren().add(anchorPane);
        }
    }

    private void searchBook(){
        txtSearch.textProperty().addListener((obj, oldValue, newValue) -> {
            FilteredList<Product> filteredList = new FilteredList<>(products, p -> {
                if (newValue == null || newValue.isEmpty()){
                    return true;
                }

                String lower = newValue.toLowerCase();
                if (p.getTitle().contains(lower.toLowerCase())){
                    return true;
                }
                return false;
            });
        });

//        generator tiles;
        createTilesProduct();
    }
}