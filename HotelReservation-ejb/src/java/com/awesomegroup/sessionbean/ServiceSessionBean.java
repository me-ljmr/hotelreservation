/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.awesomegroup.sessionbean;
 
import com.awesomegroup.entity.Service;
import java.util.ArrayList;
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
public class ServiceSessionBean implements ServiceSessionBeanRemote, ServiceSessionBeanLocal {
 @PersistenceContext(unitName="HotelReservation-ejbPU")
    private EntityManager em ;
    @Override
    public Service get(int id) { 
        return (Service)em.find(Service.class, id);
    }
    
    @Override
    public List getAll() {
         
        return em.createNamedQuery("Service.findAll").getResultList();
    }
    @Override
    public List getServiceRoomCount(){
        return em.createNamedQuery("Service.findTotalServiceRoomCount").getResultList();
    }
        @Override
    public int count(){
        return em.createNamedQuery("Service.count",Long.class) 
                .getSingleResult().intValue();
        
       // return 0;
    }
    @Override
    public List getServicesAsCollection(int[] serviceIdLists){
        List services=new ArrayList();
        for(int x :serviceIdLists){
            services.add(this.get(x));
        }
        return services;
    }
    @Override
    public void delete(int id) {
        em.remove(get(id));
    }

    @Override
    public void save(Service service) {
        if(service.getId() == null || service.getId() == 0) {
            em.persist(service);
        } else {
            em.merge(service);
        }
    
    }
    
    @Override
    public void save(Object service) {
        save((Service) service);
    }
}
