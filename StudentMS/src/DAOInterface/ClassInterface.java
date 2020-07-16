/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOInterface;

import java.util.List;
import Entity.Class;
/**
 *
 * @author khanh
 */
public interface ClassInterface {
    public Class insert(Class newClass);
    
    public boolean update(Class upClass);
    
    public boolean delete(int classId);
    
    public Class getClass(int classId);
    
    public List<Class> getAllClass();
}
