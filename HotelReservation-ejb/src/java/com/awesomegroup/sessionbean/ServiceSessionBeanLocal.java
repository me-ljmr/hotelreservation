/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.awesomegroup.sessionbean;

import com.awesomegroup.entity.Service;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author lujamanandhar
 */
@Local
public interface ServiceSessionBeanLocal {
     List getAll();

    Service get(int id);
    
    void delete(int id);

    void save(Service service);
}
