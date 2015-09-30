package com.service.cars;





import java.sql.Connection;  
import java.sql.DriverManager;  
import java.sql.PreparedStatement;  
import java.sql.ResultSet;  
import java.sql.SQLException;  
import java.sql.*;
public class LoginDao {  
    public static boolean validate(String name, String pass) {          
       boolean status = false;  
        Connection conn = null;  
        PreparedStatement pst = null;  
        ResultSet rs = null;  
  System.out.println("Here in Login");
        String url = "jdbc:mysql://localhost/";  
        String dbName = "car_pool";  
        String driver = "com.mysql.jdbc.Driver";  
        String userName = "root";  
        String password = "mycatsql";  
        try {  
        	 System.out.println("Here in Login before");
            Class.forName(driver).newInstance();  
            System.out.println("Here in Login after");
            conn = DriverManager  
                    .getConnection(url + dbName, userName, password);  
  
            pst = conn  
                    .prepareStatement("select * from user where user_name=? and password=?");  
            pst.setString(1, name);  
            pst.setString(2, pass);  
  
            rs = pst.executeQuery();  
            status = rs.next();  
  
        } catch (Exception e) {  
            System.out.println(e);  
        } finally {  
           if (conn != null) {  
                try {  
                    conn.close();  
                } catch (SQLException e) {  
                    e.printStackTrace();  
                }  
           }  
            if (pst != null) {  
                try {  
                    pst.close();  
               } catch (SQLException e) {  
                   e.printStackTrace();  
               }  
            }  
            if (rs != null) {  
                try {  
                    rs.close();  
                } catch (SQLException e) {  
                    e.printStackTrace();  
                }  
            }  
        }  
        return status;  
    }  
}  
