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
public class Attendance {
    private int id;
    private Student student;
    private Schedule schedule;
    private String date;
    private int status;

    public Attendance() {
    }

    public Attendance(Student student, Schedule schedule, String date,  int status) {
        this.student = student;
        this.schedule = schedule;
        this.date = date;
        this.status = status;
    }

    public Attendance(int id, Student student, Schedule schedule, String date, int status) {
        this.id = id;
        this.student = student;
        this.schedule = schedule;
        this.date = date;
        this.status = status;
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

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    
    public static Attendance insert(Attendance insertAttendance) throws SQLException{
        String sql = "insert into attendance(studentId, scheduleId, date, status) values(?, ?, ?, ?)";
        Connection connection = DbConnector.getConnection();
        if(connection != null){
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, insertAttendance.getStudent().getId());
            ps.setInt(2, insertAttendance.getSchedule().getId());
            ps.setString(3, insertAttendance.getDate());
            ps.setInt(4, insertAttendance.getStatus());
            int rowInserted = ps.executeUpdate();
            if(rowInserted == 1){
                ResultSet rs = ps.getGeneratedKeys();
                rs.next();
                int id = rs.getInt(1);
                insertAttendance.setId(id);
                return insertAttendance;
            }
        }
        return null;
    }
    
    public static boolean update(Attendance updateAttendance) throws SQLException{
        String sql = "update attendance set status = ? where id = ?";
        Connection connection = DbConnector.getConnection();
        if(connection != null){
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, updateAttendance.getStatus());
            ps.setInt(2, updateAttendance.getId());
            int rowUpdated = ps.executeUpdate();
            if(rowUpdated == 1){
                return true;
            }
        }
        return false;
    }
    
    public static List<Attendance> getAllAttendance(int studentId, int classId, int subjectId) throws SQLException{
        Connection connection = DbConnector.getConnection();
        List<Attendance> attendances = null;
        if(connection != null){
            Statement st = connection.createStatement();
            Schedule schedule = Schedule.getSchedule(classId, subjectId);
            ResultSet rs = st.executeQuery("select * from attendance where studentId = " + studentId + " and scheduleId = " + schedule.getId());
            attendances = new ArrayList<>();
            while(rs.next()){
                Attendance a = new Attendance(rs.getInt("id"), Student.getStudent(rs.getInt("studentId")), schedule,rs.getString("date") , rs.getInt("status"));
                attendances.add(a);
            }
        }
        return attendances;
    }
    
    public static List<Attendance> getAllAttendance(Schedule schedule, String date) throws SQLException{
        Connection connection = DbConnector.getConnection();
        List<Attendance> attendances = null;
        if(connection != null){
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("select * from attendance where scheduleId = " + schedule.getId() + " and date = '" + date+ "'");
            attendances = new ArrayList<>();
            while(rs.next()){
                Attendance a = new Attendance(rs.getInt("id"), Student.getStudent(rs.getInt("studentId")), schedule,rs.getString("date") , rs.getInt("status"));
                attendances.add(a);
            }
        }
        return attendances;
    }
    
    public static boolean delete(int scheduleId, String date) throws SQLException{
        String sql = "delete from attendance where scheduleId = ? and date = ?";
        Connection connection = DbConnector.getConnection();
        if(connection != null){
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, scheduleId);
            ps.setString(2, date);
            int rowDeleted = ps.executeUpdate();
            if(rowDeleted > 0){
                return true;
            }
        }
        return false;
        
    }
    
    public static Attendance getAttendance(Student student, Schedule schedule, String date) throws SQLException{
        Connection connection = DbConnector.getConnection();
        String sql = "select * from attendance where studentId = " + student.getId() + " and scheduleId = " + schedule.getId() + " and date = '" + date + "'";
        if(connection != null){
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if(rs.next()){
                Attendance attendance = new Attendance(rs.getInt("id"), student, schedule, date, rs.getInt("status"));
                return attendance;
            }
        }
        return null;
    }
}
