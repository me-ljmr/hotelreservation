/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.awesomegroup.sessionbean;

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
public class UserInfoSessionBean implements UserInfoSessionBeanRemote, UserInfoSessionBeanLocal {
    
    @PersistenceContext(unitName="HotelReservation-ejbPU")
    private EntityManager em ;

    @Override
    public List getAll(){
        Query query = em.createNamedQuery("UserInfo.findAll");
        return query.getResultList();
    }
    
    

    @Override
    public UserInfo get(int id){
        UserInfo userInfo = (UserInfo)em.find(UserInfo.class, id);
        return userInfo;
    }
    
    @Override
    public void delete(int id){
        em.remove(get(id));
    }

    @Override
    public void save(UserInfo userInfo){
        if(userInfo.getId() == null || userInfo.getId() == 0) {
            em.persist(userInfo);
        } else {
            em.merge(userInfo);
        }
    }
    

    @Override
    public Object getByUserEmail(String email) {
      return em.createNamedQuery("UserInfo.findByEmail")
               .setParameter("email", email)
               .getSingleResult();
    }
    
    public void save(Object userInfo) {
        save((UserInfo) userInfo);
    }
}
