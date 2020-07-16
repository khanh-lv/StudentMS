/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this tstlate file, choose Tools | Tstlates
 * and open the tstlate in the editor.
 */
package DAO;

import Connection.DbConnection;
import DAOInterface.StudentInterface;
import Entity.Student;
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
public class StudentDAO implements StudentInterface{

    @Override
    public boolean insert(Student st) {
        Connection connection = null;
        String sql = "insert into student(student_id,name,birthdate,gender,address,email,phone,acc_id) values(?)"
                    + "values(?,?,?,?,?,?,?,?)";
        try{
            connection = DbConnection.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, st.getStudentId());
            ps.setString(2, st.getName());
            ps.setString(3, st.getBirthdate());
            ps.setString(4, st.getGender());
            ps.setString(5, st.getAddress());
            ps.setString(6, st.getEmail());
            ps.setString(7, st.getPhone());
            ps.setInt(8, st.getAccId());
            int rowInserted = ps.executeUpdate();
            return rowInserted == 1;
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            return false;
        } 
    }

    @Override
    public boolean update(Student st) {
        Connection connection = null;
        String sql = "update student set "
                + "name = ?, birthdate = ?, gender = ?, address = ?, email = ?, phone = ?, acc_id = ?"
                + " where st_id = ?";
        try{
            connection = DbConnection.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(8, st.getStudentId());
            ps.setString(1, st.getName());
            ps.setString(2, st.getBirthdate());
            ps.setString(3, st.getGender());
            ps.setString(4, st.getAddress());
            ps.setString(5, st.getEmail());
            ps.setString(6, st.getPhone());
            ps.setInt(7, st.getAccId());
            int rowUpdated = ps.executeUpdate();
            return rowUpdated == 1;
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean delete(String studentId) {
        Connection connection = null;
        String sql = "delete from student where student_id = ?";
        try{
            connection = DbConnection.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, studentId);
            int rowInserted = ps.executeUpdate();
            return rowInserted == 1;
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            return false;
        }
    }

    @Override
    public Student getStudent(String studentId) {
        Connection connection = null;
        try{
            connection = DbConnection.getConnection();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("select * from student where student_id =' " + studentId + "' ");
            if(rs.next()){
                Student tmp = new Student();
                tmp.setStudentId(rs.getString("student_id"));
                tmp.setName(rs.getString("name"));
                tmp.setBirthdate(rs.getString("birthdate"));
                tmp.setGender(rs.getString("gender"));
                tmp.setAddress(rs.getString("address"));
                tmp.setEmail(rs.getString("email"));
                tmp.setPhone(rs.getString("phone"));
                tmp.setAccId(rs.getInt("acc_id"));
                return tmp;
            } else{
                return null;
            }
            
        } catch(SQLException e){
            System.err.println(e.getMessage());
            return null;
        }
    }

    @Override
    public Student getStudent(int accId) {
        Connection connection = null;
        try{
            connection = DbConnection.getConnection();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("select * from student, account where account.acc_id = " + accId + " and student.acc_id = account.acc_id");
            if(rs.next()){
                Student tmp = new Student();
                tmp.setStudentId(rs.getString("student_id"));
                tmp.setName(rs.getString("name"));
                tmp.setBirthdate(rs.getString("birthdate"));
                tmp.setGender(rs.getString("gender"));
                tmp.setAddress(rs.getString("address"));
                tmp.setEmail(rs.getString("email"));
                tmp.setPhone(rs.getString("phone"));
                tmp.setAccId(rs.getInt("acc_id"));
                return tmp;
            } else{
                return null;
            }
            
        } catch(SQLException e){
            System.err.println(e.getMessage());
            return null;
        }
    }

    @Override
    public List<Student> getAllStudent() {
        Connection connection = null;
        List<Student> stList = null;
        try{
            connection = DbConnection.getConnection();
            stList = new ArrayList<>();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("select * from student");
            while(rs.next()){
                Student tmp = new Student();
                tmp.setStudentId(rs.getString("st_id"));
                tmp.setName(rs.getString("name"));
                tmp.setBirthdate(rs.getString("birthdate"));
                tmp.setGender(rs.getString("gender"));
                tmp.setAddress(rs.getString("address"));
                tmp.setEmail(rs.getString("email"));
                tmp.setPhone(rs.getString("phone"));
                tmp.setAccId(rs.getInt("acc_id"));
                stList.add(tmp);
            } 
            
        } catch(SQLException e){
            System.err.println(e.getMessage());
        }
        return stList;
    }
    
}
