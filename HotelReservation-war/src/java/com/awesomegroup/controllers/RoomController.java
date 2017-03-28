/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.awesomegroup.controllers;

import com.awesomegroup.entity.Room;
import com.awesomegroup.entity.RoomType;
import com.awesomegroup.sessionbean.RoomSessionBeanRemote;
import com.awesomegroup.sessionbean.RoomTypeSessionBeanRemote;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author lujamanandhar
 */
@Controller
public class RoomController {
    
    @Autowired
    private ServletContext sc;
    
    @Autowired
    private HttpSession httpsession;
    
    private InitialContext ctx;
    private RoomSessionBeanRemote sessionBean;
    private RoomTypeSessionBeanRemote getRoomTypeSessionRemote(){
        try {
            ctx = new InitialContext();
            //httpsession.setAttribute("roomtyperemote",null);
            if (httpsession.getAttribute("roomtyperemote")==null){
                httpsession.setAttribute("roomtyperemote",ctx.lookup(RoomTypeSessionBeanRemote.class.getName()));
            }
        } catch (NamingException ex) {
            Logger.getLogger(RoomController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (RoomTypeSessionBeanRemote)httpsession.getAttribute("roomtyperemote");
    }
    private RoomSessionBeanRemote getRoomSessionRemote(){
        
        try {
            ctx = new InitialContext();
            //httpsession.setAttribute("roomremote",null);
            if (httpsession.getAttribute("roomremote")==null){
                httpsession.setAttribute("roomremote",ctx.lookup(RoomSessionBeanRemote.class.getName()));
            }
        } catch (NamingException ex) {
            Logger.getLogger(RoomController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (RoomSessionBeanRemote)httpsession.getAttribute("roomremote");
    }
    @RequestMapping("admin/rooms")
    public String getRooms(Model model){
        List<Room> rooms = getRoomSessionRemote().getAll();
        model.addAttribute("rooms",rooms); 
         //System.out.println(getRoomSessionRemote().getAll());
        return "roomsadmin";
    }
    
    @RequestMapping(value="/admin/rooms/new", method=RequestMethod.GET)
    public String newRoom(Model model){
        
        model.addAttribute("roomtypes",getRoomTypeSessionRemote().getAll());
        model.addAttribute("roomtype",new RoomType());
        model.addAttribute("rooms", getRoomSessionRemote().getAll());   
        return "addroomadmin";
    }
    
    @RequestMapping(value="/admin/rooms/new" , method=RequestMethod.POST)
    public String saveRoom(Model model, HttpServletRequest request, HttpServletResponse response){
        String roomnumber = request.getParameter("roomnumber");
        int floor = Integer.parseInt(request.getParameter("floor"));
        RoomType roomtype =(RoomType) getRoomTypeSessionRemote().get(Integer.parseInt(request.getParameter("roomtype")));
        
        Room room = new Room();
        room.setRoomNumber(request.getParameter("roomnumber"));
        room.setFloor(floor);
        room.setRoomTypeId(roomtype);
        sessionBean = getRoomSessionRemote();
        sessionBean.save(room);
        model.addAttribute("roomsaved", "Room with number " + roomnumber + " has been saved successfully.");
        model.addAttribute("rooms",getRoomSessionRemote().getAll());
        
        return "roomsadmin";
    }
}
