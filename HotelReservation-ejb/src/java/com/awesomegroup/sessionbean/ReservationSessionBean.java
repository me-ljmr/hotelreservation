/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.awesomegroup.sessionbean;

import com.awesomegroup.entity.Reservation;
import java.util.Date;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
 

/**
 *
 * @author n01060117
 */
@TransactionManagement(value = TransactionManagementType.CONTAINER)
@LocalBean
@Stateless
public class ReservationSessionBean implements ReservationSessionBeanRemote, ReservationSessionBeanLocal {

    @PersistenceContext(unitName = "HotelReservation-ejbPU")
    private EntityManager em;

    @Override
    public List findAll() { 
        return em.createNamedQuery("Reservation.findAll").getResultList();
    }
    @Override
    public List findReservations(Date from, Date to, int minimumPrice, int maximumPrice,int floor) {
//         return this.findAll();
        return em.createNamedQuery("Reservation.findReservations")
                .setParameter("from", from).setParameter("to", to)
                .setParameter("priceFrom", minimumPrice)
                .setParameter("priceTo",maximumPrice)
                .setParameter("floor", floor).getResultList();
    }
    @Override
    public Reservation find(int id) {
         
        return (Reservation) em.find(Reservation.class, id);
    }
    
    

    @Override
    public void delete(int id) {
        em.remove(id);
    }

    @Override
    public void save(Object reservation) {
        save((Reservation) reservation);
    }
 
    @Override
    public void save(Reservation reservation) {
        if (reservation.getId() == null || reservation.getId() == 0) {
            em.persist(reservation);
        } else {
            em.merge(reservation);
        }
    }

    @Override
    public int countBookings() {
        return em.createNamedQuery("Reservation.countBookings",Long.class).getSingleResult().intValue();       
    }
 
 
}
