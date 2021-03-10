package com.company.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ControllerWindowInfo {

    @FXML
    private ImageView coverView;

    @FXML
    private Label labelTitle;

    @FXML
    private Label labelCost;

    @FXML
    private Label labelIsStatus;

    public void initialize(){
        labelTitle.setText(ControllerMainWindow.title);
        labelCost.setText(ControllerMainWindow.cost);
        labelIsStatus.setText(ControllerMainWindow.isActive);
        coverView.setImage(new Image(ControllerMainWindow.coverView));
    }

}

