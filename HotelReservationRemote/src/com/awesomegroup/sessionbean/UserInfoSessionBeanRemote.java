/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.awesomegroup.sessionbean;

import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author Aishwarya
 */
@Remote
public interface UserInfoSessionBeanRemote {
    List getAll();
    
    Object get(int id);
    
    void delete(int id);

    void save(Object userInfo);
    Object getByUserEmail(String email);
}
