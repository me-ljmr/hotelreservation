/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.awesomegroup.sessionbean;

import java.util.List;

/**
 *
 * @author Aishwarya
 */
public interface RoomCalendarSessionBeanRemote {
    List getAll();

    Object get(int id);
}
