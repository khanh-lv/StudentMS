/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Connection.DbConnection;
import DAOInterface.ClassInterface;
import Entity.Class;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author khanh
 */
public class ClassDAO implements ClassInterface{

    @Override
    public Class insert(Class newClass) {
        Connection connect = null;
        String sql = "insert into class(class_name) values(?)";
        ResultSet rs = null;
        try {
            connect = DbConnection.getConnection();
            PreparedStatement ps = connect.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, newClass.getClassName());
            int rowInserted = ps.executeUpdate();
            
            if(rowInserted == 1){
                rs = ps.getGeneratedKeys();
                rs.next();
                int newClassId = rs.getInt("class_id");
                newClass.setClassId(newClassId);
                return newClass;
            } else{
                return null;
            }
            
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            return null;
        }
    }

    @Override
    public boolean update(Class upClass) {
        Connection connection = null;
        String sql = "update class set class_name = ? where class_id = ?";
        try{
            connection = DbConnection.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, upClass.getClassName());
            ps.setInt(2, upClass.getClassId());
            int rowUpdated = ps.executeUpdate();
            return rowUpdated == 1;
            
        }catch(SQLException e){
            System.err.println(e.getMessage());
            return false;
        }
        
    }

    @Override
    public boolean delete(int classId) {
        Connection connection =  null;
        String sql = "delete from class where class_id = ?";
        
        try{
            connection = DbConnection.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, classId);
            int rowDeleted = ps.executeUpdate();
            return rowDeleted == 1;
        } catch(SQLException e){
            System.err.println(e.getMessage());
            return false;
        }
    }

    @Override
    public Class getClass(int classId) {
        Connection connection = null;
        try{
            connection = DbConnection.getConnection();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("select * from class where class_id = " + classId);
            if(rs.next()){
                Class data = new Class(rs.getInt("class_id"), rs.getString("class_name"));
                return data;
            } else{
                return null;
            }
        }catch(SQLException e){
            System.err.println(e.getMessage());
            return null;
        }
    }

    @Override
    public List<Class> getAllClass() {
        Connection connection = null;
        List<Class> classList = null;
        try{
            connection = DbConnection.getConnection();
            classList = new ArrayList<>();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("select * from class");
            while (rs.next()) {
               Class tmp = new Class(rs.getInt("class_id"), rs.getString("class_name"));
               classList.add(tmp);         
            }
        }catch(SQLException e){
            System.err.println(e.getMessage());
        }
        return classList;
    }
    
}
