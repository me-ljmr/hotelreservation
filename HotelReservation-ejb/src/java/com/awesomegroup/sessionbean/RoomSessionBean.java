/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.awesomegroup.sessionbean;

import com.awesomegroup.entity.Room;
import com.awesomegroup.entity.Service;
import java.util.Collection;
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
 * @author lujamanandhar
 */
@TransactionManagement(value=TransactionManagementType.CONTAINER)
@LocalBean
@Stateless
public class RoomSessionBean implements RoomSessionBeanRemote, RoomSessionBeanLocal {
 
    
    @PersistenceContext(unitName="HotelReservation-ejbPU")
    private EntityManager em;

    

    @Override
    public Room get(int id) {
         
        return (Room)em.find(Room.class, id);
    }
    
    @Override
    public List getRoomsByRoomType(int roomTypeId){
        return em.createNamedQuery("Room.findByRoomType",Room.class)
                .setParameter("roomTypeId", roomTypeId)
                .getResultList();
    }
    @Override
    public List getRoomsByServiceId(int serviceId){
         
         
        return em.createNamedQuery("Room.findByServiceId",Room.class)
                .setParameter("serviceId", serviceId)
                .getResultList();
    }
    
    @Override 
    public List getRoomsByServices(Collection<Object> services){
        int[] arr  = new int[services.size()];
        int x=0;
        for(Object s :services){
            arr[x] = ((Service)s).getId();
            x++;
        }
        return em.createNamedQuery("Room.findByServices",Room.class)
                .setParameter("services", arr)
                .getResultList();
          
    } 
    
    @Override
    public List getAll() {
         
          
        return em.createNamedQuery("Room.findAll",Room.class).getResultList();
        
    }
    
    
    @Override
    public void delete(int id) {
        em.remove(get(id));
    }

    @Override
    public void save(Room room) {
        if(room.getId() == null || room.getId() == 0) {
            em.persist(room);
        } else {
            em.merge(room);
        }
    
    }
    
    @Override
    public void save(Object room) {
        save((Room) room);
    }

    @Override
    public List getRoomsByFloorNumber(int floor) {
        Query query = em.createNamedQuery("Room.findByFloor");
        return query.getResultList();
    }

    @Override
    public Room getDetail(String roomNumber) {
        Query query = em.createNamedQuery("Room.findByFloor");
        return (Room)query.getSingleResult();
    }

  
    

}