/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author xuuan
 */
public class Connect {
    private String connectionString = "jdbc:sqlserver://LAPTOP-JMI09MML:1433;" 
            + "databaseName=QuanLyNhanVien;user=sa;password=11111;"
            + "encrypt=true;trustServerCertificate=true";
    private static Connection conn;

    // Dùng singleton pattern để chỉ có một kết nối duy nhất
    public Connect() {
        try {
            if (conn == null || conn.isClosed()) {  // Kiểm tra kết nối chưa được tạo hoặc đã đóng
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                conn = DriverManager.getConnection(connectionString);
                if (conn != null) {
                    System.out.println("Ket noi thanh cong");
                }
            }
        } catch (ClassNotFoundException ex) {
            System.err.println("Loi: " + ex.getMessage());
        } catch (SQLException ex) {
            System.err.println("loi: " + ex.getMessage());
        }
    }

    public static Connection getConnection() {
        if (conn == null) {
            new Connect();  // Tạo kết nối nếu chưa có
        }
        return conn;
    }

    // Đảm bảo đóng kết nối khi ứng dụng đóng
    public static void closeConnection() {
        if (conn != null) {
            try {
                conn.close();
                System.out.println("Ket noi da dong.");
            } catch (SQLException ex) {
                System.err.println("Loi: " + ex.getMessage());
            }
        }
    }

    // Thực thi câu lệnh SELECT
    public ResultSet executeQuery(String sqlQuery) {
        try {
            Statement stmt = conn.createStatement();
            return stmt.executeQuery(sqlQuery);
        } catch (SQLException ex) {
            System.out.println("Loi: " + ex.getMessage());
        }
        return null;
    }

    // Thực thi INSERT, DELETE, UPDATE
    public void executeUpdate(String sqlQuery) {
        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(sqlQuery);
        } catch (SQLException ex) {
            System.out.println("Loi: " + ex.getMessage());
        }
    }
}

