package com.jdbc.db;

import java.sql.*;

/**
 * 数据库
 *
 * Created by yuanyin on 16/1/30.
 */
public class DBUtil {
    public static final String URL = "jdbc:mysql://localhost:3306/managementSystem";
    public static final String USER = "root";
    public static final String PASSWORD = "yywjm999569";

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
        Statement stmt = connection.createStatement();
        ResultSet resultSet = stmt.executeQuery("SELECT user_name,age FROM student");
        while (resultSet.next()) {
            System.out.println(resultSet.getString("user_name") + "," + resultSet.getInt("age"));
        }
    }
}
