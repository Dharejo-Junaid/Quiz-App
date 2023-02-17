module com.example.newquiz {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.newquiz to javafx.fxml;
    exports com.example.newquiz;
}