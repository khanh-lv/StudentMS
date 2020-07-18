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
    public Student insert(Student st) {
        Connection connection = null;
        String sql = "insert into student(student_id,name,birthdate,gender,address,email,phone,acc_id) values(?)"
                    + "values(?,?,?,?,?,?,?,?)";
        ResultSet key = null;
        try{
            connection = DbConnection.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, st.getRollNo());
            ps.setString(2, st.getName());
            ps.setString(3, st.getBirthdate());
            ps.setString(4, st.getGender());
            ps.setString(5, st.getAddress());
            ps.setString(6, st.getEmail());
            ps.setString(7, st.getPhone());
            ps.setInt(8, st.getAccId());
            int rowInserted = ps.executeUpdate();
            if(rowInserted == 1){
                key = ps.getGeneratedKeys();
                key.next();
                int student_id = key.getInt(1);
                st.setStudentId(student_id);
                return st;
            } else{
                return null;
            }
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            return null;
        } 
    }

    @Override
    public boolean update(Student st) {
        Connection connection = null;
        String sql = "update student set "
                + "rollNo = ?, name = ?, birthdate = ?, gender = ?, address = ?, email = ?, phone = ?, acc_id = ?"
                + " where st_id = ?";
        try{
            connection = DbConnection.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, st.getRollNo());
            ps.setString(2, st.getName());
            ps.setString(3, st.getBirthdate());
            ps.setString(4, st.getGender());
            ps.setString(5, st.getAddress());
            ps.setString(6, st.getEmail());
            ps.setString(7, st.getPhone());
            ps.setInt(8, st.getAccId());
            ps.setInt(9, st.getStudentId());
            int rowUpdated = ps.executeUpdate();
            return rowUpdated == 1;
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean delete(String rollNo) {
        Connection connection = null;
        String sql = "delete from student where rollNo = ?";
        try{
            connection = DbConnection.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, rollNo);
            int rowInserted = ps.executeUpdate();
            return rowInserted == 1;
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            return false;
        }
    }

    @Override
    public Student getStudent(String rollNo) {
        Connection connection = null;
        try{
            connection = DbConnection.getConnection();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("select * from student where rollNo = " + rollNo + "' ");
            if(rs.next()){
                Student tmp = new Student();
                tmp.setStudentId(rs.getInt("student_id"));
                tmp.setRollNo(rs.getString("rollNo"));
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
                tmp.setStudentId(rs.getInt("student_id"));
                tmp.setRollNo(rs.getString("rollNo"));
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
                tmp.setStudentId(rs.getInt("student_id"));
                tmp.setRollNo(rs.getString("rollNo"));
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
