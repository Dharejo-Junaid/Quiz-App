package com.example.newquiz;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.stage.StageStyle;

public class App extends Application {

    static Stage primaryStage;

    @Override
    public void start(Stage stage) throws Exception {
        primaryStage=stage;

        FXMLLoader loader=new FXMLLoader
                (getClass().getResource("Login.fxml"));

        Scene scene=new Scene(loader.load());

        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
