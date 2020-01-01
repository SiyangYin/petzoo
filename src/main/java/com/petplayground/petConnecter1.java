package com.petplayground;

import java.sql.Connection;
import java.sql.DriverManager;
//import java.sql.SQLException;
import java.sql.*;

public class petConnecter1 {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class clazz = Class.forName("com.mysql.cj.jdbc.Driver");
        final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
        final String DB_URL = "jdbc:mysql://localhost:3306/db1";
//    jdbc:mysql://localhost/db?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC

        //  Database credentials
        final String USER = "root";
        final String PASS = "";
        Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
        Statement statement = connection.createStatement();
        String sql = "select * from pet";
        ResultSet rs = statement.executeQuery(sql);

        while (rs.next()) {
            System.out.println(rs.getString(1));
        }
//        System.out.println("A");
    }

}
