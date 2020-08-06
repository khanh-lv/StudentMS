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
public class Schedule {

    private int id;
    private ClassObj classNo;
    private Subject subject;
    private String teacher;
    private String teachingFrameTime;
    private int teachingTime;
    private String startDate;
    private String endDate;
    private int status;
    List<Attendance> attendanceList;

    public Schedule(int id, ClassObj classNo, Subject subject, String teacher, String teachingFrameTime, int teachingTime, String startDate, String endDate, int status) {
        this.id = id;
        this.classNo = classNo;
        this.subject = subject;
        this.teacher = teacher;
        this.teachingFrameTime = teachingFrameTime;
        this.teachingTime = teachingTime;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
    }

    public Schedule(ClassObj classNo, Subject subject, String teacher, String teachingFrameTime, int teachingTime, String startDate, String endDate) {
        this.classNo = classNo;
        this.subject = subject;
        this.teacher = teacher;
        this.teachingFrameTime = teachingFrameTime;
        this.teachingTime = teachingTime;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Schedule() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ClassObj getClassNo() {
        return classNo;
    }

    public void setClassNo(ClassObj classNo) {
        this.classNo = classNo;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getTeachingFrameTime() {
        return teachingFrameTime;
    }

    public void setTeachingFrameTime(String teachingFrameTime) {
        this.teachingFrameTime = teachingFrameTime;
    }

    public int getTeachingTime() {
        return teachingTime;
    }

    public void setTeachingTime(int teachingTime) {
        this.teachingTime = teachingTime;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<Attendance> getAttendanceList() {
        return attendanceList;
    }

    public void setAttendanceList(List<Attendance> attendanceList) {
        this.attendanceList = attendanceList;
    }

    public static Schedule insert(Schedule insertSchedule) throws SQLException {
        String sql = "insert into schedule(classId, subjectId, teacher, teachingFrameTime, teachingTime, startDate, endDate) values(?, ?, ?, ?, ?, ?, ?)";
        Connection connection = DbConnector.getConnection();
        if (connection != null) {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, insertSchedule.getClassNo().getId());
            ps.setInt(2, insertSchedule.getSubject().getId());
            ps.setString(3, insertSchedule.getTeacher());
            ps.setString(4, insertSchedule.getTeachingFrameTime());
            ps.setInt(5, insertSchedule.getTeachingTime());
            ps.setString(6, insertSchedule.getStartDate());
            ps.setString(7, insertSchedule.getEndDate());
            int rowInserted = ps.executeUpdate();
            if (rowInserted == 1) {
                ResultSet rs = ps.getGeneratedKeys();
                rs.next();
                int id = rs.getInt(1);
                insertSchedule.setId(id);
                return insertSchedule;
            }
        }
        return null;

    }

    public static boolean update(Schedule updateSchedule) throws SQLException {
        String sql = "update schedule set "
                + "classId = ?, subjectId = ?, teacher = ?, teachingFrameTime = ?, teachingTime = ?, startDate = ?, endDate = ?, status = ? where id = ?";
        Connection connection = DbConnector.getConnection();
        if (connection != null) {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, updateSchedule.getClassNo().getId());
            ps.setInt(2, updateSchedule.getSubject().getId());
            ps.setString(3, updateSchedule.getTeacher());
            ps.setString(4, updateSchedule.getTeachingFrameTime());
            ps.setInt(5, updateSchedule.getTeachingTime());
            ps.setString(6, updateSchedule.getStartDate());
            ps.setString(7, updateSchedule.getEndDate());
            ps.setInt(8, updateSchedule.getStatus());
            ps.setInt(9, updateSchedule.getId());
            int rowupdated = ps.executeUpdate();
            if (rowupdated == 1) {
                return true;
            }
        }
        return false;
    }

    public static Schedule getSchedule(int classId, int subjectId) throws SQLException {
        Connection connection = DbConnector.getConnection();

        if (connection != null) {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("select * from schedule where classId = " + classId + " and subjectId = " + subjectId);
            if (rs.next()) {
                Schedule schedule = new Schedule();
                schedule.setId(rs.getInt("id"));
                schedule.setClassNo(ClassObj.getClass(rs.getInt("classId")));
                schedule.setSubject(Subject.getSubject(rs.getInt("subjectId")));
                schedule.setTeacher(rs.getString("teacher"));
                schedule.setTeachingFrameTime(rs.getString("teachingFrameTime"));
                schedule.setTeachingTime(rs.getInt("teachingTime"));
                schedule.setStartDate(rs.getString("startDate"));
                schedule.setEndDate(rs.getString("endDate"));
                schedule.setStatus(rs.getInt("status"));
                return schedule;
            }

        }
        return null;
    }

    public static List<Schedule> getAllSchedulesByClass(int classId) throws SQLException {
        Connection connection = DbConnector.getConnection();
        List<Schedule> schedules = null;
        if (connection != null) {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("select * from schedule where classId = " + classId);
            schedules = new ArrayList<>();
            while(rs.next()){
                Schedule schedule = new Schedule();
                schedule.setId(rs.getInt("id"));
                schedule.setClassNo(ClassObj.getClass(rs.getInt("classId")));
                schedule.setSubject(Subject.getSubject(rs.getInt("subjectId")));
                schedule.setTeacher(rs.getString("teacher"));
                schedule.setTeachingFrameTime(rs.getString("teachingFrameTime"));
                schedule.setTeachingTime(rs.getInt("teachingTime"));
                schedule.setStartDate(rs.getString("startDate"));
                schedule.setEndDate(rs.getString("endDate"));
                schedule.setStatus(rs.getInt("status"));
                schedules.add(schedule);
                
            }
            
        }
        return schedules;
    }

}
