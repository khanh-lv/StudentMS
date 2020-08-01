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
public class Employee {


    private int id;
    private String employeenum;
    private String fullname;
    private String birthdate;
    private String gender;
    private String address;
    private String email;
    private String phoneNo;
    private int status;
    private Account account;

    public Employee() {
    }

    public Employee(int id, String employeenum, String fullname, String birthdate, String gender, String address, String email, String phoneNo, int status, Account account) {
        this.id = id;
        this.employeenum = employeenum;
        this.fullname = fullname;
        this.birthdate = birthdate;
        this.gender = gender;
        this.address = address;
        this.email = email;
        this.phoneNo = phoneNo;
        this.status = status;
        this.account = account;
    }

    public Employee(String employeenum, String fullname, String birthdate, String gender, String address, String email, String phoneNo, Account account) {
        this.employeenum = employeenum;
        this.fullname = fullname;
        this.birthdate = birthdate;
        this.gender = gender;
        this.address = address;
        this.email = email;
        this.phoneNo = phoneNo;
        this.status = 1;
        this.account = account;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmployeenum() {
        return employeenum;
    }

    public void setEmployeenum(String employeenum) {
        this.employeenum = employeenum;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public static Employee insert(Employee insertEmployee) throws SQLException {
        String sql = "insert into employee(empNum, fullname, birthdate, gender, address, email, phoneNo, accId) values(?, ?, ?, ?, ?, ?, ?, ?)";
        Connection connection = DbConnector.getConnection();
        if (connection != null) {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, insertEmployee.getEmployeenum());
            ps.setString(2, insertEmployee.getFullname());
            ps.setString(3, insertEmployee.getBirthdate());
            ps.setString(4, insertEmployee.getGender());
            ps.setString(5, insertEmployee.getAddress());
            ps.setString(6, insertEmployee.getEmail());
            ps.setString(7, insertEmployee.getPhoneNo());
            ps.setInt(8, insertEmployee.getAccount().getId());

            int rowInserted = ps.executeUpdate();
            if (rowInserted == 1) {
                ResultSet rs = ps.getGeneratedKeys();
                rs.next();
                int id = rs.getInt(1);
                insertEmployee.setId(id);
                return insertEmployee;
            } else {
                return null;
            }
        }

        return null;
    }

    public static boolean update(Employee updateEmployee) throws SQLException {
        String sql = "update employee set "
                + "empNum = ?, fullname = ?, birthdate = ?, gender = ?, address = ?, email = ?, phoneNo = ?, status = ? "
                + "where id = ?";
        Connection connection = DbConnector.getConnection();
        if (connection != null) {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, updateEmployee.getEmployeenum());
            ps.setString(2, updateEmployee.getFullname());
            ps.setString(3, updateEmployee.getBirthdate());
            ps.setString(4, updateEmployee.getGender());
            ps.setString(5, updateEmployee.getAddress());
            ps.setString(6, updateEmployee.getEmail());
            ps.setString(7, updateEmployee.getPhoneNo());
            ps.setInt(8, updateEmployee.getStatus());
            ps.setInt(9, updateEmployee.getId());
            int rowUpdated = ps.executeUpdate();
            if (rowUpdated == 1) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    public static Employee getEmployee(String empNum) throws SQLException {
        Connection connection = DbConnector.getConnection();
        if (connection != null) {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("select * from employee where empNum = '" + empNum + "'");
            if (rs.next()) {
                Employee employee = new Employee();
                employee.setId(rs.getInt("id"));
                employee.setEmployeenum(rs.getString("empNum"));
                employee.setFullname(rs.getString("fullname"));
                employee.setBirthdate(rs.getString("birthdate"));
                employee.setGender(rs.getString("gender"));
                employee.setAddress(rs.getString("address"));
                employee.setEmail(rs.getString("email"));
                employee.setPhoneNo(rs.getString("phoneNo"));
                employee.setStatus(rs.getInt("status"));
                employee.setAccount(Account.getAccoutnt(rs.getInt("accId")));
                return employee;
            }
        }

        return null;
    }

    public static Employee getEmployee(int accId) throws SQLException {
        Connection connection = DbConnector.getConnection();
        if (connection != null) {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("select employee.* from employee, account where employee.accId = " + accId + " and employee.accId = account.id");
            if (rs.next()) {
                Employee employee = new Employee();
                employee.setId(rs.getInt("id"));
                employee.setEmployeenum(rs.getString("empNum"));
                employee.setFullname(rs.getString("fullname"));
                employee.setBirthdate(rs.getString("birthdate"));
                employee.setGender(rs.getString("gender"));
                employee.setAddress(rs.getString("address"));
                employee.setEmail(rs.getString("email"));
                employee.setPhoneNo(rs.getString("phoneNo"));
                employee.setStatus(rs.getInt("status"));
                employee.setAccount(Account.getAccoutnt(rs.getInt("accId")));
                return employee;
            }
        }

        return null;
    }

    public static boolean isExist(String empNum) throws SQLException {
        Connection connection = DbConnector.getConnection();
        if (connection != null) {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("select * from employee where empNum = '" + empNum + "'");
            if (rs.next()) {
                return true;
            }
        }

        return false;
    }

    public static List<Employee> getAllEmployee() throws SQLException {
        Connection connection = DbConnector.getConnection();
        List<Employee> employees = null;
        if (connection != null) {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("select employee.* from employee");
            employees = new ArrayList<>();
            while (rs.next()) {
                Employee employee = new Employee();
                employee.setId(rs.getInt("id"));
                employee.setEmployeenum(rs.getString("empNum"));
                employee.setFullname(rs.getString("fullname"));
                employee.setBirthdate(rs.getString("birthdate"));
                employee.setGender(rs.getString("gender"));
                employee.setAddress(rs.getString("address"));
                employee.setEmail(rs.getString("email"));
                employee.setPhoneNo(rs.getString("phoneNo"));
                employee.setStatus(rs.getInt("status"));
                employee.setAccount(Account.getAccoutnt(rs.getInt("accId")));
                employees.add(employee);
            }
        }

        return employees;
    }

    public static List<Employee> findByName(String nameStr) throws SQLException {
        Connection connection = DbConnector.getConnection();
        List<Employee> employees = null;
        if (connection != null) {
            if(nameStr != null) {
                Statement st = connection.createStatement();
                ResultSet rs = st.executeQuery("select * from employee where fullname like '%" + nameStr + "%'");
                employees = new ArrayList<>();
                while (rs.next()) {
                    Employee employee = new Employee();
                    employee.setId(rs.getInt("id"));
                    employee.setEmployeenum(rs.getString("empNum"));
                    employee.setFullname(rs.getString("fullname"));
                    employee.setBirthdate(rs.getString("birthdate"));
                    employee.setGender(rs.getString("gender"));
                    employee.setAddress(rs.getString("address"));
                    employee.setEmail(rs.getString("email"));
                    employee.setPhoneNo(rs.getString("phoneNo"));
                    employee.setStatus(rs.getInt("status"));
                    employee.setAccount(Account.getAccoutnt(rs.getInt("accId")));
                    employees.add(employee);
                }       
            } else {
                employees = getAllEmployee();
            }
        }
        return employees;
    }
    
    public static List<Employee> filter(String role) throws SQLException {
        Connection connection = DbConnector.getConnection();
        List<Employee> employees = null;
        String sql = "select employee.* from employee, account, role where employee.accId = account.id and account.roleId = role.ID and role.role = '" + role + "'";
        if (connection != null) {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(sql);
            employees = new ArrayList<>();
            while (rs.next()) {
                Employee employee = new Employee();
                employee.setId(rs.getInt("id"));
                employee.setEmployeenum(rs.getString("empNum"));
                employee.setFullname(rs.getString("fullname"));
                employee.setBirthdate(rs.getString("birthdate"));
                employee.setGender(rs.getString("gender"));
                employee.setAddress(rs.getString("address"));
                employee.setEmail(rs.getString("email"));
                employee.setPhoneNo(rs.getString("phoneNo"));
                employee.setStatus(rs.getInt("status"));
                employee.setAccount(Account.getAccoutnt(rs.getInt("accId")));
                employees.add(employee);
            }
        }
        return employees;
    }
}
