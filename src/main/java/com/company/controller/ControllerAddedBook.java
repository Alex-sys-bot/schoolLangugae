package com.company.controller;

import com.company.dao.Dao;
import com.company.model.Manufacturer;
import com.company.model.Product;
import com.company.service.ServiceManufacturer;
import com.company.service.ServiceProduct;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;


public class ControllerAddedBook {

    ObservableList<Manufacturer> manufacturers = FXCollections.observableArrayList();

    private final SessionFactory factory;
    public ControllerAddedBook() {
        factory = new Configuration().configure().buildSessionFactory();
    }

    @FXML
    private TextField txtNameBook;

    @FXML
    private TextField txtCost;

    @FXML
    private TextField txtPathToCover;

    @FXML
    private ComboBox<String> comboIsActive;

    @FXML
    private ComboBox<Manufacturer> comboManufacturer;

    @FXML
    private TextField txtNameManufacturer;



    public void initialize(){
        initData();
    }


    private void initData(){
        Dao<Manufacturer, Integer> daoManufacture = new ServiceManufacturer(factory);
        manufacturers.addAll(daoManufacture.readAll());
        comboManufacturer.setItems(manufacturers);

        ObservableList<String> listIsActive = FXCollections.observableArrayList("активен","не активен");
        comboIsActive.setItems(listIsActive);
    }


    @FXML
    public void buttonAddBook(ActionEvent event) {
        Dao<Product, Integer> daoProduct = new ServiceProduct(factory);
        Product product = new Product();

        Dao<Manufacturer, Integer> daoManufacturer = new ServiceManufacturer(factory);
        Manufacturer manufacturer = new Manufacturer();


        product.setTitle(txtNameBook.getText());
        product.setCost(Integer.parseInt(txtCost.getText()));
        product.setMainImagePath(txtPathToCover.getText());
        product.setIsActive(comboIsActive.getValue());

        if (comboManufacturer.getValue() != null && txtNameManufacturer.getText().isEmpty()){
            product.setManufacturers(comboManufacturer.getValue());
        } else {
            manufacturer.setName(txtNameManufacturer.getText());
            manufacturer.setLastVisitDate(new Date());
            daoManufacturer.save(manufacturer);
            product.setManufacturers(manufacturer);
        }

        daoProduct.save(product);
    }

    @FXML
    public void buttonSelectPath(){
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Select path");
        File file = chooser.showOpenDialog(new Stage());
        txtPathToCover.setText(file.getAbsolutePath());
    }
}
