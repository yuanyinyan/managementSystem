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

    private static Connection connection;

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection= DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        return connection;
    }
}
