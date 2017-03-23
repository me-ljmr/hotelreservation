/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.awesomegroup.sessionbean;

import com.awesomegroup.entity.RoomType;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author lujamanandhar
 */
@Stateless
public class RoomTypeSessionBean implements RoomTypeSessionBeanRemote, RoomTypeSessionBeanLocal {

    @PersistenceContext(unitName="HotelReservation-ejbPU")
    private EntityManager em ;

    

    @Override
    public RoomType get(int id) {
        RoomType roomType = (RoomType)em.find(RoomType.class, id);
        return roomType;
    }
    
    @Override
    public List getAll() {
        Query query = em.createNamedQuery("Room.findAll");
        return query.getResultList();
    }
    
    @Override
    public void delete(int id) {
        em.remove(get(id));
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
