/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.awesomegroup.controllers;

import com.awesomegroup.entity.AdminInfo;
import com.awesomegroup.sessionbean.ReservationSessionBeanRemote;
import com.awesomegroup.sessionbean.RoomSessionBeanRemote;
import com.awesomegroup.sessionbean.RoomTypeSessionBeanRemote;
import com.awesomegroup.sessionbean.ServiceSessionBeanRemote;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.servlet.ServletContext;
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
public class AdminDashboardController {
    
    @Autowired
    private ServletContext sc;
    
    @Autowired
    private HttpSession httpsession;
    
    private InitialContext ctx;
    private RoomSessionBeanRemote getRoombeanRemote(){
        ctx = null;
        try{
        ctx = new InitialContext(); 
        if (httpsession.getAttribute("roomsessionremote")==null){
            httpsession.setAttribute("roomsessionremote",ctx.lookup(RoomSessionBeanRemote.class.getName()));
        }
        }catch(Exception ex){
            Logger.getLogger(AdminDashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (RoomSessionBeanRemote)httpsession.getAttribute("roomsessionremote");
    }
    private RoomTypeSessionBeanRemote getRoomtypebeanRemote(){
                ctx = null;
        try{
        ctx = new InitialContext(); 
        if (httpsession.getAttribute("roomtypesessionremote")==null){
            httpsession.setAttribute("roomtypesessionremote",ctx.lookup(RoomTypeSessionBeanRemote.class.getName()));
        }
        }catch(Exception ex){
            Logger.getLogger(AdminDashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (RoomTypeSessionBeanRemote)httpsession.getAttribute("roomtypesessionremote");
    }
    
    private ServiceSessionBeanRemote getServicebeanRemote(){
             ctx = null;
        try{
        ctx = new InitialContext(); 
        if (httpsession.getAttribute("servicesessionremote")==null){
            httpsession.setAttribute("servicesessionremote",ctx.lookup(ServiceSessionBeanRemote.class.getName()));
        }
        }catch(Exception ex){
            Logger.getLogger(AdminDashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (ServiceSessionBeanRemote)httpsession.getAttribute("servicesessionremote");
    }
     private ReservationSessionBeanRemote getReservationbeanRemote(){
             ctx = null;
        try{
        ctx = new InitialContext(); 
        if (httpsession.getAttribute("reservationremote")==null){
            httpsession.setAttribute("reservationremote",ctx.lookup(ReservationSessionBeanRemote.class.getName()));
        }
        }catch(Exception ex){
            Logger.getLogger(AdminDashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (ReservationSessionBeanRemote)httpsession.getAttribute("reservationremote");
    } 
    
    @RequestMapping(value="admin/dashboard", method=RequestMethod.GET)
    public String showdashboard(Model model){
        if (httpsession.getAttribute("whoisloggedinasadmin")==null){
            return "redirect:login";
        }
        model.addAttribute("admininfo",(AdminInfo)httpsession.getAttribute("whoisloggedinasadmin"));
        // sending counters 
        model.addAttribute("roomscount",getRoombeanRemote().count());
        model.addAttribute("servicescount",getServicebeanRemote().count());
        model.addAttribute("roomtypescount",getRoomtypebeanRemote().count());
        model.addAttribute("bookingscount",getReservationbeanRemote().countBookings());
        model.addAttribute("roomscountbyservice",getServicebeanRemote().getServiceRoomCount());
        return "admin/dashboard";
    }
//    @RequestMapping(value="admin/login",method=RequestMethod.POST)
//    public String logmein(Model model, HttpServletRequest request){
//        
//    }
}
