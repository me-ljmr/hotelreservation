/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.awesomegroup.controllers;

import com.awesomegroup.sessionbean.RoomSessionBeanRemote;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
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
    
      
    private RoomSessionBeanRemote sessionBean;
    
    @RequestMapping("admin/rooms")
    public String getRooms(Model model){
         
        InitialContext ctx;
        try {
            
            ctx = new InitialContext();
            if (httpsession.getAttribute("remote")==null){
                httpsession.setAttribute("remote",ctx.lookup(RoomSessionBeanRemote.class.getName()));
            }
            RoomSessionBeanRemote sessionBean = (RoomSessionBeanRemote)httpsession.getAttribute("remote");
            model.addAttribute("rooms",sessionBean.getAll());
        } catch (NamingException ex) {
            Logger.getLogger(RoomController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
//        InitialContext ctx;
//        try{
//            httpsession.setAttribute("remote",null);
//            if(httpsession.getAttribute("remote")==null){
//                ctx = new InitialContext();
//                httpsession.setAttribute("remote", ctx.lookup(RoomSessionBeanRemote.class.getName()));
//            }
////            ctx = new InitialContext();
//            sessionBean = (RoomSessionBeanRemote)httpsession.getAttribute("remote");
//            
////            // Get all customers
//            List<Room> rooms = sessionBean.getAll();
//            model.addAttribute("error","");
//            model.addAttribute("rooms", rooms);
//            
//        }catch(NamingException ex){
//            Logger.getLogger(RoomController.class.getName()).log(Level.SEVERE, null, ex);
//            model.addAttribute("error",ex.getMessage());
//        }
        
        return "roomsadmin";
    }
    
    @RequestMapping(value="/admin/rooms/new", method=RequestMethod.GET)
    public String newRoom(Model model){
        
        return "addroomadmin";
    }
    
    @RequestMapping(value="/admin/rooms/new" , method=RequestMethod.POST)
    public String saveRoom(Model model, HttpServletRequest request){
        String roomnumber = request.getParameter("roomnumber");
         
        model.addAttribute("roomsaved", "Room with number " + roomnumber + " has been saved successfully.");
            //return "roomsadmin";
        
        return "roomsadmin";
    }
}
