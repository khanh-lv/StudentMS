/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOInterface;

import Entity.Account;

/**
 *
 * @author khanh
 */
public interface AccountInterface {
    public Account insert(Account account);
    public boolean update(Account account);
    public boolean delete(int accId);
    public Account getAccount(String username, String password);
    
}
