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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author khanh
 */
public class Role {

    private int id;

    private String role;

    public Role(int id, String role) {
        this.id = id;
        this.role = role;
    }

    public Role() {
        this.id = 0;
        this.role = "none";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public static Role insert(String role) throws SQLException {
        Connection connection = DbConnector.getConnection();
        String sql = "insert into role(role) values(?)";
        if (connection != null) {

            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, role);
            int rowInserted = ps.executeUpdate();
            if (rowInserted == 1) {
                ResultSet rs = ps.getGeneratedKeys();
                rs.next();
                int id = rs.getInt(1);
                Role newRole = new Role(id, role);
                return newRole;
            }

        }
        System.err.println("Không thể kết nối đến server. Vui lòng xem lại");
        return null;
    }

    public static List<Role> getAllRole() throws SQLException {
        List<Role> roles = null;
        Connection connection = DbConnector.getConnection();
        if (connection != null) {

            roles = new ArrayList<>();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("select * from role");
            while (rs.next()) {
                Role role = new Role(rs.getInt("id"), rs.getString("role"));
                roles.add(role);
            }

        }
        return roles;
    }

    public static Role getRole(String role) throws SQLException {
        Connection connection = DbConnector.getConnection();

        if (connection != null) {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("select * from role where role ='" + role + "'");
            if (rs.next()) {
                Role data = new Role(rs.getInt("id"), rs.getString("role"));
                return data;
            } else {
                System.out.println("khong tim thay role");
                return null;
            }
        } else {
            System.err.println("Khong the truy xuat du lieu");
            return null;
        }

    }
    
    public static Role getRole(int roleId) throws SQLException {
        Connection connection = DbConnector.getConnection();

        if (connection != null) {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("select * from role where id ='" + roleId + "'");
            if (rs.next()) {
                Role data = new Role(rs.getInt("id"), rs.getString("role"));
                return data;
            } else {
                System.out.println("khong tim thay role");
                return null;
            }
        } else {
            System.err.println("Khong the truy xuat du lieu");
            return null;
        }

    }
}
