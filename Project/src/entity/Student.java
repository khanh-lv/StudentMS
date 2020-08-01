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
public class Student {

    private int id;
    private String rollNo;
    private String fullName;
    private String birthdate;
    private String gender;
    private String address;
    private String email;
    private String phoneNo;
    private ClassObj classNo;
    private int status;
    private List<Mark> markList;
    private List<Attendance> attendanceList;

    public Student() {
    }

    public Student(int id, String rollNo, String fullName, String birthdate, String gender, String address, String email, String phoneNo, ClassObj classNo, int status) {
        this.id = id;
        this.rollNo = rollNo;
        this.fullName = fullName;
        this.birthdate = birthdate;
        this.gender = gender;
        this.address = address;
        this.email = email;
        this.phoneNo = phoneNo;
        this.classNo = classNo;
        this.status = status;
    }

    public Student(String rollNo, String fullName, String birthdate, String gender, String address, String email, String phoneNo, ClassObj classNo) {
        this.rollNo = rollNo;
        this.fullName = fullName;
        this.birthdate = birthdate;
        this.gender = gender;
        this.address = address;
        this.email = email;
        this.phoneNo = phoneNo;
        this.classNo = classNo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRollNo() {
        return rollNo;
    }

    public void setRollNo(String rollNo) {
        this.rollNo = rollNo;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public ClassObj getClassNo() {
        return classNo;
    }

    public void setClassNo(ClassObj classNo) {
        this.classNo = classNo;
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

    public List<Attendance> getAttendanceList() {
        return attendanceList;
    }

    public void setAttendanceList(List<Attendance> attendanceList) {
        this.attendanceList = attendanceList;
    }

    public static Student insert(Student insertStudent) throws SQLException {
        String sql = "insert into student(rollNo, fullname, birthdate, gender, address, email, phoneNo, classNo) values(?, ?, ?, ?, ?, ?, ?, ?)";

        Connection connection = DbConnector.getConnection();
        if (connection != null) {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, insertStudent.getRollNo());
            ps.setString(2, insertStudent.getFullName());
            ps.setString(3, insertStudent.getBirthdate());
            ps.setString(4, insertStudent.getGender());
            ps.setString(5, insertStudent.getAddress());
            ps.setString(6, insertStudent.getEmail());
            ps.setString(7, insertStudent.getPhoneNo());
            ps.setString(8, insertStudent.getClassNo().getClassNo());
            int rowInserted = ps.executeUpdate();
            if (rowInserted == 1) {
                ResultSet rs = ps.getGeneratedKeys();
                rs.next();
                int id = rs.getInt(1);
                insertStudent.setId(id);
                return insertStudent;
            }
        }
        return null;
    }

    public static boolean update(Student updateStudent) throws SQLException {
        String sql = "update student set "
                + "rollNo = ?, fullname = ?, birthdate = ?, gender = ?, address = ?, email = ?, phoneNo = ?, classNo = ?, status = ? where id = ?";

        Connection connection = DbConnector.getConnection();
        if (connection != null) {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, updateStudent.getRollNo());
            ps.setString(2, updateStudent.getFullName());
            ps.setString(3, updateStudent.getBirthdate());
            ps.setString(4, updateStudent.getGender());
            ps.setString(5, updateStudent.getAddress());
            ps.setString(6, updateStudent.getEmail());
            ps.setString(7, updateStudent.getPhoneNo());
            ps.setString(8, updateStudent.getClassNo().getClassNo());
            ps.setInt(9, updateStudent.getStatus());
            ps.setInt(10, updateStudent.getId());
            int rowUpdated = ps.executeUpdate();
            if (rowUpdated == 1) {
                return true;
            }
        }
        return false;
    }

    public static Student getStudent(String rollNo) throws SQLException {
        Connection connection = DbConnector.getConnection();
        if (connection != null) {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("select * from student where rollNo = '" + rollNo + "'");
            if (rs.next()) {
                Student student = new Student();
                student.setId(rs.getInt("id"));
                student.setRollNo(rs.getString("rollNo"));
                student.setFullName(rs.getString("fullname"));
                student.setBirthdate(rs.getString("birthdate"));
                student.setGender(rs.getString("gender"));
                student.setAddress(rs.getString("address"));
                student.setEmail(rs.getString("email"));
                student.setPhoneNo(rs.getString("phoneNo"));
                student.setClassNo(ClassObj.getClass(rs.getString("classNo")));
                student.setStatus(rs.getInt("status"));
                return student;
            }
        }
        return null;
    }

    public static boolean isExist(String rollNo) throws SQLException {
        Connection connection = DbConnector.getConnection();
        if (connection != null) {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("select * from student where rollNo = '" + rollNo + "'");
            if (rs.next()) {
                return true;
            }
        }
        return false;
    }

    public static List<Student> getAllStudent() throws SQLException {
        Connection connection = DbConnector.getConnection();
        List<Student> students = null;
        if (connection != null) {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("select * from student");
            students = new ArrayList<>();
            while (rs.next()) {
                Student student = new Student();
                student.setId(rs.getInt("id"));
                student.setRollNo(rs.getString("rollNo"));
                student.setFullName(rs.getString("fullname"));
                student.setBirthdate(rs.getString("birthdate"));
                student.setGender(rs.getString("gender"));
                student.setAddress(rs.getString("address"));
                student.setEmail(rs.getString("email"));
                student.setPhoneNo(rs.getString("phoneNo"));
                student.setClassNo(ClassObj.getClass(rs.getString("classNo")));
                student.setStatus(rs.getInt("status"));
                students.add(student);

            }
        }
        return students;
    }

    public static List<Student> getAllStudent(String classNo) throws SQLException {
        Connection connection = DbConnector.getConnection();
        List<Student> students = null;
        if (connection != null) {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("select * from student, class where student.classNo = '" + classNo + "' and class.classNo = student.classNo");
            students = new ArrayList<>();
            while (rs.next()) {
                Student student = new Student();
                student.setId(rs.getInt("id"));
                student.setRollNo(rs.getString("rollNo"));
                student.setFullName(rs.getString("fullname"));
                student.setBirthdate(rs.getString("birthdate"));
                student.setGender(rs.getString("gender"));
                student.setAddress(rs.getString("address"));
                student.setEmail(rs.getString("email"));
                student.setPhoneNo(rs.getString("phoneNo"));
                student.setClassNo(ClassObj.getClass(rs.getString("classNo")));
                student.setStatus(rs.getInt("status"));
                students.add(student);

            }
        }
        return students;
    }

    public static List<Student> findByName(String nameStr) throws SQLException {
        Connection connection = DbConnector.getConnection();
        List<Student> students = null;
        if (connection != null) {
            if (nameStr != null) {
                Statement st = connection.createStatement();
                ResultSet rs = st.executeQuery("select * from student where fullname like '%" + nameStr + "%'");
                students = new ArrayList<>();
                while (rs.next()) {
                    Student student = new Student();
                    student.setId(rs.getInt("id"));
                    student.setRollNo(rs.getString("rollNo"));
                    student.setFullName(rs.getString("fullname"));
                    student.setBirthdate(rs.getString("birthdate"));
                    student.setGender(rs.getString("gender"));
                    student.setAddress(rs.getString("address"));
                    student.setEmail(rs.getString("email"));
                    student.setPhoneNo(rs.getString("phoneNo"));
                    student.setClassNo(ClassObj.getClass(rs.getString("classNo")));
                    student.setStatus(rs.getInt("status"));
                    students.add(student);

                }
            }else{
                students = getAllStudent();
            }
        }
        return students;
    }

}
