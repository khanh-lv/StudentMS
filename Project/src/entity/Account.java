/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import dbconnector.DbConnector;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author khanh
 */
public class Account {
    private int id;
    private String username;
    private String password;
    private Role role;
    private int status;
    private Employee employee;
    public Account() {
    }

    public Account(String username, String password, Role role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public Account(int id, String username, String password, Role role, int status) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    
    public static Account insert(Account insertAccount){
        String sql = "insert into account(username, password, roleId) values(?, ?, ?)";
        Connection connection = DbConnector.getConnection();
        if(connection != null){
            try {
                PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, insertAccount.getUsername());
                ps.setString(2, insertAccount.getPassword());
                ps.setInt(3, insertAccount.getRole().getId());
                int rowInserted = ps.executeUpdate();
                if(rowInserted == 1){
                    ResultSet rs = ps.getGeneratedKeys();
                    rs.next();
                    int id = rs.getInt(1);
                    insertAccount.setId(id);
                    return insertAccount;
                }else{
                    return null;
                }
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
                return null;
            }
        }
        System.err.println("insert khong thanh cong.");
        return null;
    }
    public static boolean update(Account updateAccount){
        String sql = "update account set " 
                    + "username = ?, password = ?, roleId = ?, status = ? where id = ?";
        Connection connection = DbConnector.getConnection();
        if(connection != null){
            try{
                PreparedStatement ps = connection.prepareStatement(sql);
                ps.setString(1, updateAccount.getUsername());
                ps.setString(2, updateAccount.getPassword());
                ps.setInt(3, updateAccount.getRole().getId());
                ps.setInt(4, updateAccount.getStatus());
                ps.setInt(5, updateAccount.getId());
                int rowUpdated = ps.executeUpdate();
                if(rowUpdated == 1){
                    System.out.println("Update thanh cong");
                    return true;
                } else{
                    System.out.println("Update that bai");
                    return false;                      
                }
                
            }catch(SQLException e){
                System.err.println(e.getMessage());
                return false;
            }
        }
        System.err.println("ket noi that bai");
        return false;
    }
    
    public static Account getAccoutnt(String username, String password) throws SQLException{
        Connection connection = DbConnector.getConnection();
        if(connection != null){
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("select * from account where username = '" + username + "' and password = '" + password + "'");
            if(rs.next()){
                Account account = new Account(rs.getInt("id"), rs.getString("username"), rs.getString("password"), Role.getRole(rs.getInt("roleId")), rs.getInt("status"));
                return account;
            } else{
                System.out.println("Khong tin thay account");
                return null;
            }
        }
        return null;
    }
    
    public static Account getAccoutnt(int accId) throws SQLException{
        Connection connection = DbConnector.getConnection();
        if(connection != null){
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("select * from account where id = " + accId);
            if(rs.next()){
                Account account = new Account(rs.getInt("id"), rs.getString("username"), rs.getString("password"), Role.getRole(rs.getInt("roleId")), rs.getInt("status"));
                return account;
            } else{
                System.out.println("Khong tin thay account");
                return null;
            }
        }
        return null;
    }
    
    public static boolean isExist(String username) throws SQLException{
        Connection connection = DbConnector.getConnection();
        if(connection != null){
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("select * from account where username = '" + username + "'");
            if(rs.next()){
                return true;
            } else{
                return false;
            }
        }
        return false;
    }
//    public static void main(String[] args) throws SQLException {
//
//        
//        System.out.println(Account.isExist("khanh@admin"));
//    }
    
}
