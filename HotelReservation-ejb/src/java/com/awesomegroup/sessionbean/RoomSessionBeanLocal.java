/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.awesomegroup.sessionbean;

import com.awesomegroup.entity.Room;
import java.util.Collection;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author lujamanandhar
 */
@Local
public interface RoomSessionBeanLocal {
     List getAll();

    Room get(int id);
    
    void delete(int id);

    void save(Room room);
    
    List getRoomsByFloorNumber(int floor);
    List getRoomsByRoomType(int roomTypeId);
    Room getDetail(String roomNumber);
    
    List getRoomsByServices(Collection<Object> services);
    
    List getRoomsByServiceId(int serviceId);
     
}
