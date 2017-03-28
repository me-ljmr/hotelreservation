/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.awesomegroup.sessionbean;

import com.awesomegroup.entity.RoomCalendar;
import com.awesomegroup.entity.UserInfo;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Aishwarya
 */
@TransactionManagement(value=TransactionManagementType.CONTAINER)
@LocalBean
@Stateless
public class RoomCalendarSessionBean implements RoomCalendarSessionBeanRemote, RoomCalendarSessionBeanLocal {
    
    @PersistenceContext(unitName="HotelReservation-ejbPU")
    private EntityManager em ;

    @Override
    public List getAll(){
        Query query = em.createNamedQuery("RoomCalendar.findAll");
        return query.getResultList();
    }

    /**
     *
     * @param id
     * @return
     */
    @Override
    public RoomCalendar get(int id) {
        RoomCalendar roomCalendar = (RoomCalendar)em.find(RoomCalendar.class, id);
        return roomCalendar;
    }

    
   
}
    
