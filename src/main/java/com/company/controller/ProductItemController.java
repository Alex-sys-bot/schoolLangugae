package com.company.controller;

import com.company.model.Product;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;



public class ProductItemController {

   @FXML
   private ImageView imageCourse;

   @FXML
   private Label lblTitle;

   @FXML
   private Label lblCost;

   @FXML
   private Label lblManufacturer;

   @FXML
   private Label lblActive;

   public void setData(Product product){
           imageCourse.setImage(new Image(product.getMainImagePath()));
           lblTitle.setText(product.getTitle());
           lblCost.setText(String.format("%.2f руб.",product.getCost()));
           lblActive.setText(product.getIsActive());
           lblManufacturer.setText(product.getManufacturers().getName());
        }
}
