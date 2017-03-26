/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.awesomegroup.controllers;

import com.awesomegroup.sessionbean.RoomSessionBeanRemote;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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
        model.addAttribute("title","Room List");
        
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
}
