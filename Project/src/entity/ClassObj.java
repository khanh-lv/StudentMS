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
public class ClassObj {
    private int id;
    private String classNo;
    private int status;
    private List<Student> studentList;
    private List<Schedule> schedulesList;
    public ClassObj(int id, String classNo, int status) {
        this.id = id;
        this.classNo = classNo;
        this.status = status;
    }

    public ClassObj(String classNo) {
        this.classNo = classNo;
    }

    public ClassObj() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getClassNo() {
        return classNo;
    }

    public void setClassNo(String classNo) {
        this.classNo = classNo;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }

    public List<Schedule> getSchedulesList() {
        return schedulesList;
    }

    public void setSchedulesList(List<Schedule> schedulesList) {
        this.schedulesList = schedulesList;
    }
    
    public static ClassObj insert(ClassObj insertClass) throws SQLException{
        String sql = "insert into class(classNo) values(?)";
        Connection connection = DbConnector.getConnection();
        if(connection != null) {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, insertClass.getClassNo());
            int rowInserted = ps.executeUpdate();
            if(rowInserted == 1){
                ResultSet rs = ps.getGeneratedKeys();
                rs.next();
                int id = rs.getInt(1);
                insertClass.setId(id);
                return insertClass;
            }
        }
        return null;
    }
    
    public static boolean update(ClassObj updateClass) throws SQLException{
        String sql = "update class set classNo = ?, status = ? where id = ?";
        Connection connection = DbConnector.getConnection();
        if(connection != null){
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, updateClass.getClassNo());
            ps.setInt(2, updateClass.getStatus());
            ps.setInt(3, updateClass.getId());
            int rowUpdate = ps.executeUpdate();
            if(rowUpdate == 1){
                return true;
            }
        }
        return false;
    }
    
    public static ClassObj getClass(String classNo) throws SQLException{
        Connection connection = DbConnector.getConnection();
        if(connection != null){
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("select * from class where classNo = '" + classNo + "'");
            if(rs.next()){
                ClassObj getClass = new ClassObj(rs.getInt("id"), rs.getString("classNo"), rs.getInt("status"));
                return getClass;
            }
        }
        return null;
    }
    
    public static List<ClassObj> getAllClass() throws SQLException{
        Connection connection = DbConnector.getConnection();
        List<ClassObj> classes = null;
        if(connection != null){
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("select * from class");
            classes = new ArrayList<>();
            while (rs.next()) {
                ClassObj getClass = new ClassObj(rs.getInt("id"), rs.getString("classNo"), rs.getInt("status"));
                classes.add(getClass);           
            }
        }
        return classes;
    }
    
    public static boolean isExist(String classNo) throws SQLException{
        Connection connection = DbConnector.getConnection();
        if(connection != null){
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("select * from class where classNo = '" + classNo + "'");
            if(rs.next()){
                return true;
            }
        }
        return false;
    }
    
    public static ClassObj getClass(int id) throws SQLException{
        Connection connection = DbConnector.getConnection();
        if(connection != null){
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("select * from class where id = " + id);
            if(rs.next()){
                ClassObj getClass = new ClassObj(rs.getInt("id"), rs.getString("classNo"), rs.getInt("status"));
                return getClass;
            }
        }
        return null;
    }
}
