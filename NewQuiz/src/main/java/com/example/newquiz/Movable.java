package com.example.newquiz;

import javafx.scene.Node;

public class Movable {

    private double x=0, y=0;

    public void setMovable(Node node){

        node.setOnMousePressed(e -> {
            x=e.getSceneX();
            y= e.getSceneY();
        });

        node.setOnMouseDragged(e -> {
            App.primaryStage.setX(e.getScreenX()-x);
            App.primaryStage.setY(e.getScreenY()-y);
        });
    }
}
