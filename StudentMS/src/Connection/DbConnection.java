/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author khanh
 */
public class DbConnection {
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";
    private static final String CONNECTSTRING = "jdbc:mysql://localhost/student_ms?serverTimezone=UTC";
    public static Connection getConnection() throws SQLException{
        return DriverManager.getConnection(CONNECTSTRING, USERNAME, PASSWORD);
    }
}
