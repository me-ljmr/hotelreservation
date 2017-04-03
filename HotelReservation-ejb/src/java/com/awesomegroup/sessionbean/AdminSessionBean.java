/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.awesomegroup.sessionbean;

import com.awesomegroup.entity.AdminInfo;
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
public class AdminSessionBean implements AdminSessionBeanRemote, AdminSessionBeanLocal {

    @PersistenceContext(unitName = "HotelReservation-ejbPU")
    private EntityManager em;

    @Override
    public List findAll() {
         
        return em.createNamedQuery("AdminInfo.findAll").getResultList();
    }

    @Override
    public AdminInfo find(int id) {
        
        return (AdminInfo) em.find(AdminInfo.class, id);
    }
    
    @Override
    public AdminInfo find(String loginname){
        return (AdminInfo )em.createNamedQuery("AdminInfo.findByLoginName")
                .setParameter("loginName", loginname)
                .getSingleResult();
    }

    @Override
    public void delete(int id) {
        em.remove(find(id));
    }

    @Override
    public void save(AdminInfo adminInfo) {
        if (adminInfo.getId() == null || adminInfo.getId() == 0) {
            em.persist(adminInfo);
        } else {
            em.merge(adminInfo);
        }
    }

    @Override
    public void save(Object adminInfo) {
        save((AdminInfo) adminInfo);
    }
}
