package com.example.newquiz;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class Quiz implements Initializable {

    @FXML
    AnchorPane anchor;

    @FXML private Label question;

    @FXML private RadioButton a;
    @FXML private RadioButton b;
    @FXML private RadioButton c;
    @FXML private RadioButton d;
    @FXML private ToggleGroup options;

    @FXML private Button saveAndNextButton;
    @FXML private Button finishButton;

    static int studentID=0;
    static int correctAnswers=0, totalQuestions=0;

    ConnectionDB connectionNow;
    Connection dbConnection;
    Statement st;
    ResultSet result;

    String key="";

    Stage stage;
    Scene scene;
    FXMLLoader loader;

    Movable mv;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        mv=new Movable();
        mv.setMovable(anchor);

        finishButton.setDisable(true);

        String query="SELECT * FROM programming_quiz;";

        try {
            connectionNow=new ConnectionDB();
            dbConnection= connectionNow.getConnection();

            st=dbConnection.createStatement();
            result=st.executeQuery(query);

            if(result.next()){
                setQuestion();
            }
        }

        catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void setQuestion() throws SQLException {

        question.setText("Q." + result.getInt(1) + " " +
                result.getString(2));

        a.setText(result.getString(3));
        b.setText(result.getString(4));
        c.setText(result.getString(5));
        d.setText(result.getString(6));
        key=result.getString(7);

        totalQuestions++;
    }

    public boolean isAnswerTrue(){

        if(a.isSelected()){
            if(a.getText().equals(key)){
                return true;
            }
        }

        else if(b.isSelected()){
            if(b.getText().equals(key)){
                return true;
            }
        }

        else if(c.isSelected()){
            if(c.getText().equals(key)){
                return true;
            }
        }

        else if(d.isSelected()){
            if(d.getText().equals(key)){
                return true;
            }
        }

        return false;
    }

    @FXML
    void onSaveAndNextAction (ActionEvent event) throws SQLException {

        if(!a.isSelected() && !b.isSelected() && !c.isSelected() && !d.isSelected()){

            return;
        }

        if(isAnswerTrue()){
            correctAnswers++;
        }

        if(result.next()){
            setQuestion();
        }

        else{
            saveAndNextButton.setDisable(true);
            finishButton.setDisable(false);
        }
    }

    @FXML
    void onFinishAction (ActionEvent event) throws Exception {

        // updating result of user;
        String newQuery="UPDATE users SET programming_result="
                +correctAnswers+" WHERE student_id="+studentID+";";
        st.executeUpdate(newQuery);

        // switching to Result scene;
        stage=(Stage)finishButton.getScene().getWindow();

        loader=new FXMLLoader(getClass().getResource("Result.fxml"));
        scene=new Scene(loader.load());
        stage.setScene(scene);
        stage.setTitle("Result");
        stage.show();
    }

    @FXML
    public void onCLoseAction(ActionEvent event) {
        System.exit(0);
    }
}
