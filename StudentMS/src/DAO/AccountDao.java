/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Connection.DbConnection;
import DAOInterface.AccountInterface;
import Entity.Account;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author khanh
 */
public class AccountDao implements AccountInterface{

    @Override
    public boolean insert(Account account) {
        String sql = "insert into account(username, password, role) values(?, ?, ?)";
        ResultSet key = null;
        try{
            Connection connection = DbConnection.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, account.getUsername());
            ps.setString(2, account.getPassword());
            ps.setString(3, account.getRole());
            int rowInserted = ps.executeUpdate();
            if(rowInserted == 1){
                key = ps.getGeneratedKeys();
                key.next();
                int accId = key.getInt(1);
                account.setAccId(accId);
                return true;
            } else{
                return false;
            }
            
        } catch(Exception e){
            System.err.println(e.getMessage());
            return false;
        }
        
        
    }

    @Override
    public boolean update(Account account) {
        String sql = "update account set"
                + " username = ?,"
                + " password = ?"
                + " role = ?"
                + " where acc_id = ?";
        
        try{
            Connection connection = DbConnection.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, account.getUsername());
            ps.setString(2, account.getPassword());
            ps.setString(3, account.getRole());
            ps.setInt(4, account.getAccId());
            int rowUpdated = ps.executeUpdate();
            if(rowUpdated == 1){
                return true;
            } else{
                return false;
            }
        } catch(Exception e){
            System.err.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean delete(int accId) {
        String sql = "delete from account where id = ?";
        try{
            Connection connection = DbConnection.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, accId);
            int rowUpdated = ps.executeUpdate();
            if(rowUpdated == 1){
                return true;
            } else{
                return false;
            }
        } catch(Exception e){
            System.err.println(e.getMessage());
            return false;
        }
    }

    @Override
    public Account getAccount(String username, String password) {
        String sql = "select * from account where username = '" + username
                + "' and password = '" + password + "'";
        Account account = null;
        try{
            Connection connection = DbConnection.getConnection();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if(rs.next()){
                account = new Account();
                account.setAccId(rs.getInt("acc_id"));
                account.setUsername(rs.getString("username"));
                account.setPassword(rs.getString("password"));
                account.setRole(rs.getString("role"));
            }
        } catch(Exception e){
            System.err.println(e.getMessage());
        }
        
        return account;
    }

    
    
}
