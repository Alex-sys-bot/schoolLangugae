package com.company;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ProgramStart extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Parent parent = FXMLLoader.load(getClass().getResource("/view/mainWindow.fxml"));
        stage.setTitle("School languages");
        stage.setScene(new Scene(parent));
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/school_logo.png")));
        stage.show();
    }

    public static void main(String[] args){
        launch(args);
    }
}
