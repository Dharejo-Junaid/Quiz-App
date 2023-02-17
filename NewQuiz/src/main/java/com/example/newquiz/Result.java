package com.example.newquiz;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class Result implements Initializable {

    @FXML
    AnchorPane anchor;

    @FXML
    private Label result;

    Movable mv;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        mv=new Movable();
        mv.setMovable(anchor);

        result.setText("You have scored  " + Quiz.correctAnswers + "  out of  " + Quiz.totalQuestions);
    }

    @FXML
    public void onCloseAction(ActionEvent event){
        System.exit(0);
    }
}
