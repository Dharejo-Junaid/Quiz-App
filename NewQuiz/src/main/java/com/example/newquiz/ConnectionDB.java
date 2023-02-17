package com.example.newquiz;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionDB {

    private Connection databaseLink;

    public Connection getConnection() throws Exception{
        String user="root";
        String password="juni100190";
        String url="jdbc:mysql://localhost/quiz";

        Class.forName("com.mysql.cj.jdbc.Driver");
        databaseLink= DriverManager.getConnection(url, user, password);

        return databaseLink;
    }
}

