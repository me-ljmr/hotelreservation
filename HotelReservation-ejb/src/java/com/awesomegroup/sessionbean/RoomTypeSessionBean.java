/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.awesomegroup.sessionbean;

import com.awesomegroup.entity.RoomType;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author lujamanandhar
 */
@TransactionManagement(value=TransactionManagementType.CONTAINER)
@LocalBean
@Stateless
public class RoomTypeSessionBean implements RoomTypeSessionBeanRemote, RoomTypeSessionBeanLocal {

    @PersistenceContext(unitName="HotelReservation-ejbPU")
    private EntityManager em ;


    @Override
    public RoomType get(int id) {
         
        return (RoomType)em.find(RoomType.class, id);
    }
    
    @Override
    public List getAll() {
        
        return em.createNamedQuery("RoomType.findAll").getResultList();
    }
    
    @Override
    public void delete(int id) {
        em.remove(get(id));
    }
    
    @Override
    public int count(){
        return  em.createNamedQuery("RoomType.count",Long.class) 
                .getSingleResult().intValue();
 
    }
    
    @Override
    public void save(RoomType roomType) {
        if(roomType.getId() == null || roomType.getId() == 0) {
            em.persist(roomType);
        } else {
            em.merge(roomType);
        }
    
    }
    
    @Override
    public void save(Object roomType) {
        save((RoomType) roomType);
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
