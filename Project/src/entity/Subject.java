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
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author khanh
 */
public class Subject {
    private int id;
    private String subjectNo;
    private String subjectName;
    private int status;
    private List<Mark> markList;
    private List<Schedule> scheduleList;

    public Subject() {
    }

    public Subject(String subjectNo, String subjectName) {
        this.subjectNo = subjectNo;
        this.subjectName = subjectName;
    }

    public Subject(int id, String subjectNo, String subjectName, int status) {
        this.id = id;
        this.subjectNo = subjectNo;
        this.subjectName = subjectName;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSubjectNo() {
        return subjectNo;
    }

    public void setSubjectNo(String subjectNo) {
        this.subjectNo = subjectNo;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<Mark> getMarkList() {
        return markList;
    }

    public void setMarkList(List<Mark> markList) {
        this.markList = markList;
    }

    public List<Schedule> getScheduleList() {
        return scheduleList;
    }

    public void setScheduleList(List<Schedule> scheduleList) {
        this.scheduleList = scheduleList;
    }
    
    
    public static Subject insert(Subject insertSubject) throws SQLException{
        String sql = "insert into subject(subjectNo, subjectName) values(?, ?)";
        Connection connection = DbConnector.getConnection();
        if(connection != null){
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, insertSubject.getSubjectNo());
            ps.setString(2, insertSubject.getSubjectName());
            int rowInserted = ps.executeUpdate();
            if(rowInserted == 1){
                ResultSet rs = ps.getGeneratedKeys();
                rs.next();
                int id = rs.getInt(1);
                insertSubject.setId(id);
                return insertSubject;
            }
        }
        return null;
    }
    
    public static boolean update(Subject updateSubject) throws SQLException{
        String sql = "update subject set "
                + "subjectNo = ?, subjectName = ?, status = ? where id = ?";
        Connection connection = DbConnector.getConnection();
        if(connection != null){
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, updateSubject.getSubjectNo());
            ps.setString(2, updateSubject.getSubjectName());
            ps.setInt(3, updateSubject.getStatus());
            ps.setInt(4, updateSubject.getId());
            int rowUpdated = ps.executeUpdate();
            if(rowUpdated == 1){
                return true;
            }
        }
        return false;
    }
    
    public static Subject getSubject(String subjectNo) throws SQLException{
        Connection connection = DbConnector.getConnection();
        if(connection != null){
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("select * from subject where subjectNo = '" + subjectNo + "'");
            if(rs.next()){
                Subject subject = new Subject(rs.getInt("id"), rs.getString("subjectNo"), rs.getString("subjectName"), rs.getInt("status"));
                return subject;
            }
        }
        return null;
    }
    
    public static Subject getSubjectByName(String subjectName) throws SQLException{
        Connection connection = DbConnector.getConnection();
        if(connection != null){
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("select * from subject where subjectName = '" + subjectName + "'");
            if(rs.next()){
                Subject subject = new Subject(rs.getInt("id"), rs.getString("subjectNo"), rs.getString("subjectName"), rs.getInt("status"));
                return subject;
            }
        }
        return null;
    }
    
    public static List<Subject> getAllSubject() throws SQLException{
        Connection connection = DbConnector.getConnection();
        List<Subject> subjects = null;
        if(connection != null){
            subjects = new ArrayList<>();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("select * from subject");
            while(rs.next()){
                Subject subject = new Subject(rs.getInt("id"), rs.getString("subjectNo"), rs.getString("subjectName"), rs.getInt("status"));
                subjects.add(subject);
            }
        }
        return subjects;
    }
    
    public static List<Subject> findByName(String nameStr) throws SQLException {
        Connection connection = DbConnector.getConnection();
        List<Subject> subjects = null;
        if (connection != null) {
            if(nameStr != null) {
                Statement st = connection.createStatement();
                ResultSet rs = st.executeQuery("select * from subject where subjectName like '%" + nameStr + "%'");
                subjects = new ArrayList<>();
                while (rs.next()) {        
                    Subject subject = new Subject();
                    subject.setId(rs.getInt("id"));
                    subject.setSubjectNo(rs.getString("subjectNo"));
                    subject.setSubjectName(rs.getString("subjectName"));
                    subject.setStatus(rs.getInt("status"));
                    subjects.add(subject);
                }       
            } 
        }
        return subjects;
    }
   
}
