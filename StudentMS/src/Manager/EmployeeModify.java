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
public class EmployeeModify {

    public static List<Employee> findAll() {
        List<Employee> employeeList = new ArrayList<>();

        Connection connection = null;
        Statement statement = null;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/student_ms", "root", "");

            String sql = "SELECT * FROM employee";
            statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                Employee emp = new Employee(
                        resultSet.getString("emp_id"),
                        resultSet.getString("name"),
                        resultSet.getString("birthdate"),
                        resultSet.getString("gender"),
                        resultSet.getString("address"),
                        resultSet.getString("email"),
                        resultSet.getString("phone"),
                        resultSet.getString("position"),
                        (Account) accId);

                employeeList.add(emp);
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

        return employeeList;
    }

    public static void insert(Employee emp) {
        Connection connection = null;
        PreparedStatement psm = null;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/student_ms", "root", "");

            String sql = "INSERT INTO employee(emp_id, name, birthdate, gender, address, email, phone, position, acc_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            psm = connection.prepareCall(sql);

            psm.setString(1, emp.getEmpId());
            psm.setString(2, emp.getName());
            psm.setString(3, emp.getBirthdate());
            psm.setString(4, emp.getGender());
            psm.setString(5, emp.getAddress());
            psm.setString(6, emp.getEmail());
            psm.setString(7, emp.getPhone());
            psm.setString(8, emp.getPosition());
            psm.setInt(9, emp.getAccId().getAccId());

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

    public static void update(Employee emp) {
        Connection connection = null;
        PreparedStatement psm = null;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/student_ms", "root", "");

            String sql = "UPDATE employee SET name=?,birthdate=?,gender=?,address=?,email=?,phone=?,position=? WHERE emp_id = ?";
            psm = connection.prepareCall(sql);

            psm.setString(1, emp.getName());
            psm.setString(2, emp.getBirthdate());
            psm.setString(3, emp.getGender());
            psm.setString(4, emp.getAddress());
            psm.setString(5, emp.getEmail());
            psm.setString(6, emp.getPhone());
            psm.setString(7, emp.getPosition());
            psm.setString(8, emp.getEmpId());

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

    public static void delete(String emp_id) {
        Connection connection = null;
        PreparedStatement psm = null;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/student_ms", "root", "");

            String sql = "DELETE FROM employee WHERE emp_id = ?";
            psm = connection.prepareCall(sql);

            psm.setString(1, emp_id);

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
