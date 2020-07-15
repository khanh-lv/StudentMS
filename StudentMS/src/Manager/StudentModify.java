/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Manager;

import static Manager.Employee_.accId;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author MTD
 */
public class StudentModify {
        public static List<Student> findAll() {
        List<Student> studentList = new ArrayList<>();

        Connection connection = null;
        Statement statement = null;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/student_ms", "root", "");

            String sql = "SELECT * FROM student";
            statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                Student std = new Student(
                        resultSet.getString("student_id"),
                        resultSet.getString("name"),
                        resultSet.getString("birthdate"),
                        resultSet.getString("gender"),
                        resultSet.getString("address"),
                        resultSet.getString("email"),
                        resultSet.getString("phone"),
                        
                        (Account) accId);

                studentList.add(std);
            }

        } catch (SQLException ex) {
            Logger.getLogger(EmployeeModify.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(EmployeeModify.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(EmployeeModify.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        return studentList;
    }

    public static void insert(Student std) {
        Connection connection = null;
        PreparedStatement psm = null;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/student_ms", "root", "");

            String sql = "INSERT INTO student(student_id, name, birthdate, gender, address, email, phone, acc_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            psm = connection.prepareCall(sql);

            psm.setString(1, std.getStudentId());
            psm.setString(2, std.getName());
            psm.setString(3, std.getBirthday());
            psm.setString(4, std.getGender());
            psm.setString(5, std.getAddress());
            psm.setString(6, std.getEmail());
            psm.setString(7, std.getPhone());
            
            psm.setInt(8, std.getAccId().getAccId());

            psm.execute();

        } catch (SQLException ex) {
            Logger.getLogger(EmployeeModify.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (psm != null) {
                try {
                    psm.close();
                } catch (SQLException ex) {
                    Logger.getLogger(EmployeeModify.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(EmployeeModify.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public static void update(Student std) {
        Connection connection = null;
        PreparedStatement psm = null;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/student_ms", "root", "");

            String sql = "UPDATE student SET name=?,birthdate=?,gender=?,address=?,email=?,phone=? WHERE student_id = ?";
            psm = connection.prepareCall(sql);

            psm.setString(1, std.getName());
            psm.setString(2, std.getBirthday());
            psm.setString(3, std.getGender());
            psm.setString(4, std.getAddress());
            psm.setString(5, std.getEmail());
            psm.setString(6, std.getPhone());
         
            psm.setString(7, std.getStudentId());

            psm.execute();

        } catch (SQLException ex) {
            Logger.getLogger(EmployeeModify.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (psm != null) {
                try {
                    psm.close();
                } catch (SQLException ex) {
                    Logger.getLogger(EmployeeModify.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(EmployeeModify.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public static void delete(String student_id) {
        Connection connection = null;
        PreparedStatement psm = null;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/student_ms", "root", "");

            String sql = "DELETE FROM student WHERE student_id = ?";
            psm = connection.prepareCall(sql);

            psm.setString(1, student_id);

            psm.execute();

        } catch (SQLException ex) {
            Logger.getLogger(EmployeeModify.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (psm != null) {
                try {
                    psm.close();
                } catch (SQLException ex) {
                    Logger.getLogger(EmployeeModify.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(EmployeeModify.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}
