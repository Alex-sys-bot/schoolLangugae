package com.company.controller;

import com.company.dao.Dao;
import com.company.model.Product;
import com.company.service.ServiceProduct;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.IOException;

public class MainController {

    @FXML
    private ScrollPane scrollPain;

    @FXML
    private TilePane tilePane;

    private MyListener myListener;


    ObservableList<Product> products = FXCollections.observableArrayList();

    public void initialize() throws IOException {
        rubberWindow();
        initData();
        generatedFullInfoFromTile();
        generatedTiles(products);
    }

    private void generatedFullInfoFromTile() throws IOException {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/productItem.fxml"));
            AnchorPane anchorPane = loader.load();

            Scene scene = new Scene(anchorPane);
            scene.setFill(Color.TRANSPARENT);

            Stage stage = new Stage();
            stage.setTitle("Full info");
            stage.setScene(scene);
            stage.setResizable(false);


            ProductItemController productItemController = loader.getController();

            myListener = new MyListener() {
                @Override
                public void setOnKeyReleased(Product product) {
                    productItemController.setData(product);
                    stage.show();
                }
            };
        }

    private void generatedTiles(ObservableList<Product> products) throws IOException {
        tilePane.setHgap(25);
        tilePane.setVgap(25);
        tilePane.setPadding(new Insets(10,0,0,0));
        tilePane.setCursor(Cursor.HAND);

        for (Product product: products) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/tile.fxml"));
            AnchorPane anchorPane = loader.load();

            TileController tileController = loader.getController();
            tileController.setData(product, myListener);

            tilePane.getChildren().add(anchorPane);
        }
    }

    private void initData(){
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        Dao<Product, Integer> daoProduct = new ServiceProduct(factory);
        products.addAll(daoProduct.readAll());
    }

    private void rubberWindow(){
        scrollPain.widthProperty().addListener((observable, oldValue,newValue) -> {
            tilePane.setPrefWidth(newValue.doubleValue());
        });
    }
}
