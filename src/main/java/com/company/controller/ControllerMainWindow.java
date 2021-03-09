package com.company.controller;

import com.company.dao.Dao;
import com.company.model.Product;
import com.company.service.ServiceProduct;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.IOException;

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
    @FXML
    private ScrollPane scrollPane;



    public void initialize(){
//        initialize from dataBase;
        initData(products);

//        initialize startWindow;
        createTilesProduct(products);

//        search by named book;
        searchBook();

    }

    private void initData(ObservableList<Product> list){
        Dao<Product, Integer> daoProduct = new ServiceProduct(factory);
        products.addAll(daoProduct.readAll());
    }

    private void createTilesProduct(ObservableList<Product> products){
            flowPane.getChildren().clear();
            scrollPane.widthProperty().addListener((observableValue, oldValue, newValue) ->{
                flowPane.setPrefWidth(newValue.intValue());
            });
            System.out.println();
            flowPane.setHgap(25);
            flowPane.setVgap(15);
            flowPane.setAlignment(Pos.CENTER_LEFT);
            flowPane.setOrientation(Orientation.HORIZONTAL);


            for (Product product : products) {
//            Contain cover;
                AnchorPane anchorPane = new AnchorPane();

//            images;
                ImageView imageView = new ImageView();
                imageView.setImage(new Image(product.getMainImagePath()));
                imageView.setFitHeight(300);
                imageView.setFitWidth(200);

//            labels;
                Label labelNamed = new Label();
                labelNamed.setText(product.getTitle());
                labelNamed.setPrefHeight(320);
                labelNamed.setPadding(new Insets(300, 0, 0, 0));
                labelNamed.setWrapText(true);
                labelNamed.setMaxWidth(200);

                Label labelCost = new Label();
                labelCost.setText("Стоимость: " + product.getCost() + " руб.");
                labelCost.setPrefHeight(320);
                labelCost.setPadding(new Insets(315, 0, 0, 0));

                Label labelIsActive = new Label();
                labelIsActive.setText("Состояние: " + product.getIsActive());
                labelIsActive.setPrefHeight(320);
                labelIsActive.setPadding(new Insets(330, 0, 0, 0));

//            Node;
                anchorPane.getChildren().add(imageView);
                anchorPane.getChildren().add(labelNamed);
                anchorPane.getChildren().add(labelCost);
                anchorPane.getChildren().add(labelIsActive);
                flowPane.getChildren().add(anchorPane);
            }
    }

    private void searchBook(){
        txtSearch.setOnKeyReleased(keyEvent -> {
            ObservableList<Product> list = FXCollections.observableArrayList();
            for (Product product: products) {
                if (product.getTitle().toLowerCase().contains(txtSearch.getText().toLowerCase())){
                    list.add(product);
                }
            }
//        generator tiles;
            createTilesProduct(list);
        });
    }

    @FXML
    public void buttonAddBook() throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("/view/windowAddedBook.fxml"));
        Stage addBook = new Stage();
        addBook.setTitle("Added book");
        addBook.initModality(Modality.APPLICATION_MODAL);
        addBook.setScene(new Scene(parent));
        addBook.showAndWait();
    }
}