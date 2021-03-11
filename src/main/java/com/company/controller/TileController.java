package com.company.controller;


import com.company.model.Product;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class TileController {

    @FXML
    private ImageView imageCourse;

    @FXML
    private Label lblTitle;

    @FXML
    private Label lblCost;

    @FXML
    private Label lblActive;

    @FXML
    private AnchorPane anchorPane;

    private MyListener myListener;

    private Product product;

    @FXML
    private void click(MouseEvent event){
        myListener.setOnKeyReleased(product);
    }

    @FXML
    private void clickExit(MouseEvent event) throws IOException {
    }


    public void setData(Product product, MyListener myListener){
        this.product = product;
        this.myListener = myListener;

        imageCourse.setImage(new Image(product.getMainImagePath()));
        lblTitle.setText(product.getTitle());
        lblCost.setText(String.format("%.2f руб.",product.getCost()));
        lblActive.setText(product.getIsActive());
    }
}
