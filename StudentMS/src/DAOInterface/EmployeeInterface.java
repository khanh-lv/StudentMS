/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOInterface;

import Entity.Employee;
import java.util.List;

/**
 *
 * @author khanh
 */
public interface EmployeeInterface {
    public boolean insert(Employee emp);
    
    public boolean update(Employee emp);
    
    public boolean delete(String  empId);
    
    public Employee getEmployee(String empId);
    
    public Employee getEmployee(int accId);
    
    public List<Employee> getAllEmployee();
    
}
