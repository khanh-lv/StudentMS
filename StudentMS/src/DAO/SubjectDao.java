/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Connection.DbConnection;
import DAOInterface.SubjectInterface;
import Entity.Subject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author khanh
 */
public class SubjectDao implements SubjectInterface{

    @Override
    public Subject insert(Subject sub) {
        Connection connect = null;
        String sql = "insert into subject(subject_name) values(?)";
        ResultSet rs = null;
        try {
            connect = DbConnection.getConnection();
            PreparedStatement ps = connect.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, sub.getSubjectName());
            int rowInserted = ps.executeUpdate();
            
            if(rowInserted == 1){
                rs = ps.getGeneratedKeys();
                rs.next();
                int newSubjectId = rs.getInt("subject_id");
                sub.setSubjectId(newSubjectId);
                return sub;
            } else{
                return null;
            }
                   
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            return null;
        }
    }

    @Override
    public boolean update(Subject sub) {
        Connection connection = null;
        String sql = "update subject set subject_name = ? where subject_id = ?";
        try{
            connection = DbConnection.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, sub.getSubjectName());
            ps.setInt(2, sub.getSubjectId());
            int rowUpdated = ps.executeUpdate();
            return rowUpdated == 1;
            
        }catch(SQLException e){
            System.err.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean delete(int subjectId) {
        Connection connection =  null;
        String sql = "delete from subject where subject_id = ?";
        
        try{
            connection = DbConnection.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, subjectId);
            int rowDeleted = ps.executeUpdate();
            return rowDeleted == 1;
        } catch(SQLException e){
            System.err.println(e.getMessage());
            return false;
        }
    }

    @Override
    public Subject getSubject(int subjectId) {
        Connection connection = null;
        try{
            connection = DbConnection.getConnection();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("select * from subject where subject_id = " + subjectId);
            if(rs.next()){
                Subject sub = new Subject(rs.getInt("subject_id"), rs.getString("subject_name"));
                return sub;
            } else{
                return null;
            }
        }catch(SQLException e){
            System.err.println(e.getMessage());
            return null;
        }
    }

    @Override
    public List<Subject> getAllSubject() {
        Connection connection = null;
        List<Subject> subList = null;
        try{
            connection = DbConnection.getConnection();
            subList = new ArrayList<>();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("select * from subject");
            while (rs.next()) {
               Subject sub = new Subject(rs.getInt("subject_id"), rs.getString("subject_name"));
               subList.add(sub);         
            }
        }catch(SQLException e){
            System.err.println(e.getMessage());
        }
        return subList;
    }
    
}
