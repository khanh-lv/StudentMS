/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbconnector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author khanh
 */
public class DbConnector {
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";
    private static final String CONNECTSTRING = "jdbc:mysql://localhost:3306/project?serverTimezone=UTC";
    
    public static Connection getConnection(){
        try {
            return DriverManager.getConnection(CONNECTSTRING, USERNAME, PASSWORD);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            return null;
        }
    }
    
}
