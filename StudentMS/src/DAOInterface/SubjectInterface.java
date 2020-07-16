/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOInterface;

import Entity.Subject;
import java.util.List;

/**
 *
 * @author khanh
 */
public interface SubjectInterface {
    public Subject insert(Subject sub);
    
    public boolean update(Subject sub);
    
    public boolean delete(int subjectId);
    
    public Subject getSubject(int subjectId);
    
    public List<Subject> getAllSubject();
}
