/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.awesomegroup.sessionbean;

import com.awesomegroup.entity.Reservation;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author n01060117
 */
@Local
public interface ReservationSessionBeanLocal {

    List findAll();

    Reservation find(int id);

    void delete(int id);

    void save(Reservation reservation);
    
    List findReservations(Date from, Date to, int minimumPrice, int maximumPrice,int floor);
}
