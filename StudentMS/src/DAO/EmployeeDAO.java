/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Connection.DbConnection;
import DAOInterface.EmployeeInterface;
import Entity.Employee;
import java.sql.Connection;
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
 * @author khanh
 */
public class EmployeeDAO implements EmployeeInterface{

    @Override
    public Employee insert(Employee emp) {
        Connection connection = null;
        String sql = "insert into employee(emp_num,name,birthdate,gender,address,email,phone,position,acc_id)"
                    + "values(?,?,?,?,?,?,?,?,?)";
        ResultSet key = null;
        try{
            connection = DbConnection.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, emp.getEmpNum());
            ps.setString(2, emp.getName());
            ps.setString(3, emp.getBirthdate());
            ps.setString(4, emp.getGender());
            ps.setString(5, emp.getAddress());
            ps.setString(6, emp.getEmail());
            ps.setString(7, emp.getPhone());
            ps.setString(8, emp.getPosition());
            ps.setInt(9, emp.getAccId());
            int rowInserted = ps.executeUpdate();
            if(rowInserted == 1){
                key = ps.getGeneratedKeys();
                key.next();
                int emp_id = key.getInt(1);
                emp.setEmpId(emp_id);
                return emp;
            }else{
                return null;
            }
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            return null;
        }  
    }

    @Override
    public boolean update(Employee emp) {
        Connection connection = null;
        String sql = "update employee set "
                + "emp_num = ?, name = ?, birthdate = ?, gender = ?, address = ?, email = ?, phone = ?, position = ?, acc_id = ?"
                + " where emp_id = ?";
        try{
            connection = DbConnection.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, emp.getEmpNum());
            ps.setString(2, emp.getName());
            ps.setString(3, emp.getBirthdate());
            ps.setString(4, emp.getGender());
            ps.setString(5, emp.getAddress());
            ps.setString(6, emp.getEmail());
            ps.setString(7, emp.getPhone());
            ps.setString(8, emp.getPosition());
            ps.setInt(9, emp.getAccId());
            ps.setInt(10, emp.getEmpId());
            int rowUpdated = ps.executeUpdate();
            return rowUpdated == 1;
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean delete(int empId) {
        Connection connection = null;
        String sql = "delete from employee where emp_id = ?";
        try{
            connection = DbConnection.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, empId);
            int rowInserted = ps.executeUpdate();
            return rowInserted == 1;
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            return false;
        }
    }

    @Override
    public Employee getEmployee(String empId) {
        Connection connection = null;
        try{
            connection = DbConnection.getConnection();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("select * from employee where emp_id =' " + empId + "' ");
            if(rs.next()){
                Employee emp = new Employee();
                emp.setEmpId(rs.getInt("emp_id"));
                emp.setEmpNum(rs.getString("emp_num"));
                emp.setName(rs.getString("name"));
                emp.setBirthdate(rs.getString("birthdate"));
                emp.setGender(rs.getString("gender"));
                emp.setAddress(rs.getString("address"));
                emp.setEmail(rs.getString("email"));
                emp.setPhone(rs.getString("phone"));
                emp.setPosition(rs.getString("position"));
                emp.setAccId(rs.getInt("acc_id"));
                return emp;
            } else{
                return null;
            }
            
        } catch(SQLException e){
            System.err.println(e.getMessage());
            return null;
        }
        
    }

    @Override
    public List<Employee> getAllEmployee() {
        Connection connection = null;
        List<Employee> empList = null;
        try{
            connection = DbConnection.getConnection();
            empList = new ArrayList<>();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("select * from employee");
            while(rs.next()){
                Employee emp = new Employee();
                emp.setEmpId(rs.getInt("emp_id"));
                emp.setEmpNum(rs.getString("emp_num"));
                emp.setName(rs.getString("name"));
                emp.setBirthdate(rs.getString("birthdate"));
                emp.setGender(rs.getString("gender"));
                emp.setAddress(rs.getString("address"));
                emp.setEmail(rs.getString("email"));
                emp.setPhone(rs.getString("phone"));
                emp.setPosition(rs.getString("position"));
                emp.setAccId(rs.getInt("acc_id"));
                empList.add(emp);
            } 
            
        } catch(SQLException e){
            System.err.println(e.getMessage());
        }
        return empList;
    }

    @Override
    public Employee getEmployee(int accId) {
        Connection connection = null;
        try{
            connection = DbConnection.getConnection();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("select * from employee, account where account.acc_id = " + accId + " and student.acc_id = account.acc_id");
            if(rs.next()){
                Employee emp = new Employee();
                emp.setEmpId(rs.getInt("emp_id"));
                emp.setEmpNum(rs.getString("emp_num"));
                emp.setName(rs.getString("name"));
                emp.setBirthdate(rs.getString("birthdate"));
                emp.setGender(rs.getString("gender"));
                emp.setAddress(rs.getString("address"));
                emp.setEmail(rs.getString("email"));
                emp.setPhone(rs.getString("phone"));
                emp.setPosition(rs.getString("position"));
                emp.setAccId(rs.getInt("acc_id"));
                return emp;
            } else{
                return null;
            }
            
        } catch(SQLException e){
            System.err.println(e.getMessage());
            return null;
        }
    }
    
}
