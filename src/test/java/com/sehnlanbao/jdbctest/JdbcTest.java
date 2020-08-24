package com.sehnlanbao.jdbctest;

import com.mysql.jdbc.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JdbcTest {
    public static final String URL = "jdbc:mysql://localhost:3306/mydb";
    public static final String USER = "root";
    public static final String PASSWORD = "123456";
    private static Connection conn = null;

    static {
        try {
//            Class.forName("com.mysql.jdbc.Driver");

            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            DriverManager.registerDriver(new Driver());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        return conn;
    }

    public static void main(String[] args) throws SQLException {
        Connection conn = JdbcTest.getConnection();
        String sql = "insert into customer (name, age) values (?, ?)";

        PreparedStatement ptmt = conn.prepareStatement(sql);

        ptmt.setString(1, "lisi");
        ptmt.setInt(2, 30);

        ptmt.execute();
    }
}
