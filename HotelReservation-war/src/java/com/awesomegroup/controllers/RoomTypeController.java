/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.awesomegroup.controllers;

import com.awesomegroup.entity.AdminInfo;
import com.awesomegroup.entity.RoomType;
import com.awesomegroup.sessionbean.RoomTypeSessionBeanRemote;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import jdk.nashorn.internal.runtime.JSType;
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
public class RoomTypeController {
    @Autowired
    private ServletContext sc;

    @Autowired
    private HttpSession httpsession;

    private InitialContext ctx;
    private RoomTypeSessionBeanRemote sessionBean;
    private RoomTypeSessionBeanRemote getRoomTypeSessionRemote(){

       try {
           ctx = new InitialContext();
            
           if (httpsession.getAttribute("roomtyperemote")==null){
               httpsession.setAttribute("roomtyperemote",ctx.lookup(RoomTypeSessionBeanRemote.class.getName()));
           }
       } catch (NamingException ex) {
           Logger.getLogger(RoomTypeController.class.getName()).log(Level.SEVERE, null, ex);
       }
       return (RoomTypeSessionBeanRemote)httpsession.getAttribute("roomtyperemote");
   }
    
       
    @RequestMapping("admin/roomtypes")
    public String getRoomTypes(Model model){
        if (httpsession.getAttribute("whoisloggedinasadmin")==null){
            return "redirect:login";
        }
        List<String> errors = null; 
        List<RoomType> roomtypes=null;
        try{
            roomtypes= getRoomTypeSessionRemote().getAll();
            
        }catch(Exception ex){
            if (errors==null) errors = new ArrayList<>();
            errors.add(ex.getMessage());
        }
        model.addAttribute("errors", errors);
        model.addAttribute("roomtypes",roomtypes);  
        model.addAttribute("admininfo",(AdminInfo)httpsession.getAttribute("whoisloggedinasadmin"));
        return "admin/roomtypes";
    }
    private boolean emptyCheck(String checkstr){
        return checkstr.isEmpty() || checkstr==null || checkstr.trim().equals("");
    }
    
    @RequestMapping(value="admin/roomtypes",method=RequestMethod.POST)
    public String roomtypepost(Model model, HttpServletRequest request){
        if (httpsession.getAttribute("whoisloggedinasadmin")==null){
            return "redirect:login";
        }
        List<String> errors = null;
        List<String> messages = null;
        String desc=""  ;
        double rate=0;
        if(request.getParameter("mode").equals("save")){
            // new or edit
            desc = request.getParameter("description");
            
            
            if(emptyCheck(request.getParameter("rate")) || request.getParameter("rate").equals("0")){
                
                if (errors==null) errors = new ArrayList<>();
                errors.add("Please enter rate value. Room cannot be free of cost.");
                
            }else{
                 
                if(!JSType.isNumber(request.getParameter("rate"))){
                    errors.add("Please enter a valid numeric rate value");
                }else{
                    rate = Double.parseDouble(request.getParameter("rate"));
                }
            }
              
            if(emptyCheck(request.getParameter("description"))){
                if (errors==null) errors = new ArrayList<>();
                errors.add("Please describe the type of room");   
            }
            if(errors==null){
                RoomType roomtype=null;
                if(request.getParameter("roomtypeid").equals("0")){
                    // new 
                     roomtype = new RoomType();
                     roomtype.setId(0);
                    

                }else{
                    //edit
                    roomtype = (RoomType)getRoomTypeSessionRemote().get(Integer.parseInt(request.getParameter("roomtypeid")));
                     
                }
                roomtype.setRate(BigDecimal.valueOf(Double.parseDouble(request.getParameter("rate"))));
                roomtype.setDescription(request.getParameter("description"));
                getRoomTypeSessionRemote().save(roomtype);
                if(messages==null) messages = new ArrayList<>();
                messages.add("Data Saved Successfully");
            }else{
                model.addAttribute("rate",rate);
                model.addAttribute("description",desc);
            }
        }else if (request.getParameter("mode").equals("delete")){
            // delete
            if (!emptyCheck(request.getParameter("roomtypeid"))){
                try{
                    getRoomTypeSessionRemote().delete(Integer.parseInt(request.getParameter("roomtypeid")));
                    if(messages==null) messages = new ArrayList<>();
                    messages.add("Data deleted successfully");
                }catch(Exception ex){
                    if(errors==null) errors = new ArrayList<>();
                    errors.add("Selected data cannot be deleted. It is assigned to some rooms.");
                }
                
            }else{
                if(errors==null) errors = new ArrayList<>();
                errors.add("Invalid room type identification number");
            }
        }
        
        List<RoomType> roomtypes=null;
        try{
            roomtypes= getRoomTypeSessionRemote().getAll();
             
        }catch(Exception ex){
            if(errors ==null) errors = new ArrayList<>();
            errors.add(ex.getMessage());
        }
        model.addAttribute("admininfo",(AdminInfo)httpsession.getAttribute("whoisloggedinasadmin"));
        model.addAttribute("messages",messages);
        model.addAttribute("errors", errors);
        model.addAttribute("roomtypes",roomtypes); 
        return "admin/roomtypes";
    }
}
