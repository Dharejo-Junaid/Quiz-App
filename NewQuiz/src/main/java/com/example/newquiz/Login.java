package com.example.newquiz;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class Login implements Initializable {

    @FXML
    AnchorPane anchor;

    @FXML
    private Label notification;

    @FXML
    private PasswordField password;

    @FXML
    private TextField username;

    private Stage stage;
    private Scene scene;
    private FXMLLoader loader;

    ConnectionDB connectionNow;
    Connection dbConnection;
    ResultSet result;

    Movable mv;

    @FXML
    void onCancelAction(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    void onCreateAccountAction(ActionEvent event) throws Exception {
        stage=(Stage)username.getScene().getWindow();

        loader=new FXMLLoader(getClass().getResource("Signup.fxml"));
        scene=new Scene(loader.load());
        stage.setScene(scene);
        stage.setTitle("Signup");
        stage.show();
    }

    @FXML
    void onLoginAction(ActionEvent event) throws Exception {
        if(username.getText().isEmpty() || password.getText().isEmpty()){
            notification.setText("No username or password");
            return;
        }

        connectionNow=new ConnectionDB();
        dbConnection=connectionNow.getConnection();

        Statement st=dbConnection.createStatement();
        String query="SELECT * FROM quiz.users;";

        result=st.executeQuery(query);

        if(checkValidity()){

            // switching to Quiz scene;
            stage=(Stage)username.getScene().getWindow();
            loader=new FXMLLoader(getClass().getResource("Quiz.fxml"));
            scene=new Scene(loader.load());
            stage.setScene(scene);
            stage.setTitle("Quiz");
            stage.show();
        }

        else{
            notification.setText("Access Denied!");
        }
    }

    // checking given username and password is correct or not;
    public boolean checkValidity() throws Exception {

        while(result.next()){
            if(username.getText().equals(result.getString(2))
                    && password.getText().equals(result.getString(3))){

                // setting value to student_id of Quiz class;
                Quiz.studentID=result.getInt(1);
                return true;
            }
        }

        return false;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        mv=new Movable();
        mv.setMovable(anchor);
    }
}