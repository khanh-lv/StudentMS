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
public class Mark {
    private int id;
    private Student student;
    private Subject subject;
    private double mark;

    public Mark() {
    }

    public Mark(int id, Student student, Subject subject, double mark) {
        this.id = id;
        this.student = student;
        this.subject = subject;
        this.mark = mark;
    }

    public Mark(Student student, Subject subject, double mark) {
        this.student = student;
        this.subject = subject;
        this.mark = mark;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public double getMark() {
        return mark;
    }

    public void setMark(double mark) {
        this.mark = mark;
    }
    
    public static Mark insertMark(Mark insertMark) throws SQLException{
        String sql = "insert into mark(studentId, subjectId, mark) values(?, ?, ?)";
        Connection connection = DbConnector.getConnection();
        if(connection != null){
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, insertMark.getStudent().getId());
            ps.setInt(2, insertMark.getSubject().getId());
            ps.setDouble(3, insertMark.getMark());
            int rowInserted = ps.executeUpdate();
            if(rowInserted == 1){
                ResultSet rs = ps.getGeneratedKeys();
                rs.next();
                int id = rs.getInt(1);
                insertMark.setId(id);
                return insertMark;
            }
        }
        return null;
    }
    
    public static boolean updatetMark(Mark updateMark) throws SQLException{
        String sql = "update mark set mark = ? where id = ?";
        Connection connection = DbConnector.getConnection();
        if(connection != null){
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setDouble(1, updateMark.getMark());
            ps.setInt(2, updateMark.getId());
            int rowUpdated = ps.executeUpdate();
            if(rowUpdated == 1){
                return true;
            }
        }
        return false;
    }
    public static boolean deletetMark(Mark deleteMark) throws SQLException{
        String sql = "delete from mark where id = ?";
        Connection connection = DbConnector.getConnection();
        if(connection != null){
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, deleteMark.getId());
            
            int rowDeleted= ps.executeUpdate();
            if(rowDeleted == 1){
                return true;
            }
        }
        return false;
    }
    
    public static Mark getMark(int studentId, int subjectId) throws SQLException{
        Connection connection = DbConnector.getConnection();
        if(connection != null){
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("select * from mark where studentId = " + studentId + " and subjectId = " + subjectId );
            if(rs.next()){
                Mark mark = new Mark(rs.getInt("id"), Student.getStudent(rs.getInt("studentId")), Subject.getSubject(rs.getInt("subjectId")), rs.getDouble("mark"));
                return mark;
            }
        }
        return null;
    }
    
    public static List<Mark> getAllMark() throws SQLException{
        Connection connection = DbConnector.getConnection();
        List<Mark> marks = null;
        if(connection != null){
            marks = new ArrayList<>();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("select * from mark" );
            while(rs.next()){
                Mark mark = new Mark(rs.getInt("id"), Student.getStudent(rs.getInt("studentId")), Subject.getSubject(rs.getInt("subjectId")), rs.getDouble("mark"));
                marks.add(mark);
            }
        }
        return marks;
    }
    
    public static List<Mark> getAllMark(int studentId) throws SQLException{
        Connection connection = DbConnector.getConnection();
        List<Mark> marks = null;
        if(connection != null){
            marks = new ArrayList<>();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("select * from mark where studentId = " + studentId );
            while(rs.next()){
                Mark mark = new Mark(rs.getInt("id"), Student.getStudent(rs.getInt("studentId")), Subject.getSubject(rs.getInt("subjectId")), rs.getDouble("mark"));
                marks.add(mark);
            }
        }
        return marks;
    }
    
    public static List<Mark> getAllMarkBySubjectNo(int subjectId) throws SQLException{
        Connection connection = DbConnector.getConnection();
        List<Mark> marks = null;
        if(connection != null){
            marks = new ArrayList<>();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("select * from mark where subjectId = " + subjectId );
            while(rs.next()){
                Mark mark = new Mark(rs.getInt("id"), Student.getStudent(rs.getInt("studentId")), Subject.getSubject(rs.getInt("subjectId")), rs.getDouble("mark"));
                marks.add(mark);
            }
        }
        return marks;
    }
    
    public static List<Mark> getMarkByClass(int subjectId, int classId) throws SQLException{
        Connection connection = DbConnector.getConnection();
        List<Mark> marks = null;
        if(connection != null){
            marks = new ArrayList<>();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("select student.rollNo, mark.subjectId, mark.mark from student LEFT JOIN mark on student.id = mark.id and mark.subjectId =" + subjectId + " where student.classId = " + classId);
            while(rs.next()){
                Mark mark = new Mark(rs.getInt("id"), Student.getStudent(rs.getInt("studentId")), Subject.getSubject(rs.getInt("subjectId")), rs.getDouble("mark"));
                marks.add(mark);
            }
        }
        return marks;
    }
    
}
