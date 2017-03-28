/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.awesomegroup.sessionbean;

import com.awesomegroup.entity.Reservation;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import sessionbean.ReservationSessionBeanRemote;

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
        Query query = em.createNamedQuery("Reservation.findAll");
        return query.getResultList();
    }

    @Override
    public Reservation find(int id) {
        Reservation reserve = (Reservation) em.find(Reservation.class, id);
        return reserve;
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

 
}
