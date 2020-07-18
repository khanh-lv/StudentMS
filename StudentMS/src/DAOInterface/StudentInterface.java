/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOInterface;

import Entity.Student;
import java.util.List;

/**
 *
 * @author khanh
 */
public interface StudentInterface {
    public Student insert(Student st);
    
    public boolean update(Student st);
    
    public boolean delete(String  rollNo);
    
    public Student getStudent(String rollNo);
    
    public Student getStudent(int accId);
    
    public List<Student> getAllStudent();
    
//    public List<Student> getAllStudentByClass(int classId);
}
