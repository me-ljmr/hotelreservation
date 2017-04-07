/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.awesomegroup.sessionbean;

import java.util.Collection;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author lujamanandhar
 */
@Remote
public interface RoomSessionBeanRemote {
     List getAll();

    Object get(int id);
    
    void delete(int id);

    void save(Object room);
    
    List getRoomsByFloorNumber(int floor);
    
    List getRoomsByRoomType(int roomTypeId);
    
    Object getDetail(String roomNumber);
    
    List getRoomsByServiceId(int serviceId);
    
    List getRoomsByServices(Collection<Object> services);
}
