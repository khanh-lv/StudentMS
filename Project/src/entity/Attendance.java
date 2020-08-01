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
        String sql = "insert into attendance(rollNo, scheduleId, date, status) values(?, ?, ?, ?)";
        Connection connection = DbConnector.getConnection();
        if(connection != null){
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, insertAttendance.getStudent().getRollNo());
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
    
    public static List<Attendance> getAllAttendance(String rollNo, String classNo, String subjectNo) throws SQLException{
        Connection connection = DbConnector.getConnection();
        List<Attendance> attendances = null;
        if(connection != null){
            Statement st = connection.createStatement();
            Schedule schedule = Schedule.getSchedule(classNo, subjectNo);
            ResultSet rs = st.executeQuery("select * from attendance where rollNo = '" + rollNo + "' and scheduleId = " + schedule.getId());
            attendances = new ArrayList<>();
            while(rs.next()){
                Attendance a = new Attendance(rs.getInt("id"), Student.getStudent(rs.getString("rollNo")), schedule,rs.getString("date") , 0);
                attendances.add(a);
            }
        }
        return attendances;
    }
}
