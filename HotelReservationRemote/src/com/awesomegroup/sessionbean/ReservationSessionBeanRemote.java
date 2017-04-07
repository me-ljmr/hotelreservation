/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.awesomegroup.sessionbean;

import java.util.Date;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author n01060117
 */
@Remote
public interface ReservationSessionBeanRemote {

    List findAll();

    Object find(int id);

    void delete(int id);

    void save(Object reservation);
    
    List findReservations(Date from, Date to, int minimumPrice, int maximumPrice,int floor);
}
