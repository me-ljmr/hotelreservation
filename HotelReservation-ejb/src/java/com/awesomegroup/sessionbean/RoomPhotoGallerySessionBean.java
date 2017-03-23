/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.awesomegroup.sessionbean;

import com.awesomegroup.entity.RoomPhotoGallery;
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
public class RoomPhotoGallerySessionBean implements RoomPhotoGalleryRemote, RoomPhotoGalleryLocal {
    @PersistenceContext(unitName="HotelReservation-ejbPU")
    private EntityManager em ;

    

    @Override
    public RoomPhotoGallery get(int id) {
        RoomPhotoGallery roomPhotoGallery = (RoomPhotoGallery)em.find(RoomPhotoGallery.class, id);
        return roomPhotoGallery;
    }
    
     
    
    @Override
    public void delete(int id) {
        em.remove(get(id));
    }

    @Override
    public void save(RoomPhotoGallery roomPhotoGallery) {
        if(roomPhotoGallery.getId() == null || roomPhotoGallery.getId() == 0) {
            em.persist(roomPhotoGallery);
        } else {
            em.merge(roomPhotoGallery);
        }
    
    }
    
    @Override
    public void save(Object roomPhotoGallery) {
        save((RoomPhotoGallery) roomPhotoGallery);
    }

    @Override
    public List getAllPicturesById(int roomId) {
        Query query = em.createNamedQuery("RoomPhotoGallery.findByRoomId").setParameter("roomId", roomId);
        
        return query.getResultList();
    }

    @Override
    public List getAllPicturesFor(String roomNumber) {
        Query query = em.createNamedQuery("RoomPhotoGallery.findByRoomNumber").setParameter("roomNumber", roomNumber);
        return query.getResultList();
    }
     
}
