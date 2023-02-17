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

public class Signup implements Initializable {

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

    private Movable mv;

    @FXML
    void onCancelAction(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    void onHaveAccountAction(ActionEvent event) throws Exception {
        stage=(Stage)username.getScene().getWindow();

        loader=new FXMLLoader(getClass().getResource("Login.fxml"));
        scene=new Scene(loader.load());
        stage.setScene(scene);
        stage.setTitle("Login");
        stage.show();
    }

    @FXML
    void onSignupAction(ActionEvent event) throws Exception {
        if(username.getText().isEmpty() || password.getText().isEmpty()){
            notification.setText("No username or password");
            return;
        }

        connectionNow=new ConnectionDB();
        dbConnection=connectionNow.getConnection();

        Statement st=dbConnection.createStatement();
        String query=new String("INSERT INTO users(username, password) VALUES('"
                + username.getText() + "', '" + password.getText() + "');");

        st.executeUpdate(query);

        // setting student_id in the variable of quiz class;
        String newQuery="SELECT student_id FROM users ORDER BY student_id DESC LIMIT 1;";
        result = st.executeQuery(newQuery);
        while(result.next()){
            Quiz.studentID=result.getInt(1);
        }

        // Quiz.studentID = (st.executeQuery(newQuery)).getInt(1);

        // switch to Quiz Scene;
        stage=(Stage)username.getScene().getWindow();
        loader=new FXMLLoader(getClass().getResource("Quiz.fxml"));
        scene=new Scene(loader.load());
        stage.setScene(scene);
        stage.setTitle("Quiz");
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        mv=new Movable();
        mv.setMovable(anchor);
    }
}

