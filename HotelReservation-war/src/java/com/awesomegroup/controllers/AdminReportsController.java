/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.awesomegroup.controllers;

import com.awesomegroup.entity.Reservation;
import com.awesomegroup.sessionbean.ReservationSessionBeanRemote;
import com.awesomegroup.sessionbean.RoomSessionBeanRemote;
import com.awesomegroup.sessionbean.ServiceSessionBeanRemote;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
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
public class AdminReportsController {
    
    @Autowired
    private HttpSession httpsession;
    private InitialContext ctx;
     
    private ServiceSessionBeanRemote getServiceSessionRemote(){

       try {
           ctx = new InitialContext();
            
           if (httpsession.getAttribute("serviceremote")==null){
               httpsession.setAttribute("serviceremote",ctx.lookup(ServiceSessionBeanRemote.class.getName()));
           }
       } catch (NamingException ex) {
           Logger.getLogger(ServiceController.class.getName()).log(Level.SEVERE, null, ex);
       }
       return (ServiceSessionBeanRemote)httpsession.getAttribute("serviceremote");
   }
     private RoomSessionBeanRemote getRoomSessionRemote(){

       try {
           ctx = new InitialContext();
            
           if (httpsession.getAttribute("roomremote")==null){
               httpsession.setAttribute("roomremote",ctx.lookup(RoomSessionBeanRemote.class.getName()));
           }
       } catch (NamingException ex) {
           Logger.getLogger(AdminReportsController.class.getName()).log(Level.SEVERE, null, ex);
       }
       return (RoomSessionBeanRemote)httpsession.getAttribute("roomremote");
   }
     private ReservationSessionBeanRemote getReservationSessionRemote(){
         try {
           ctx = new InitialContext();
            
           if (httpsession.getAttribute("reservationremote")==null){
               httpsession.setAttribute("reservationremote",ctx.lookup(ReservationSessionBeanRemote.class.getName()));
           }
       } catch (NamingException ex) {
           Logger.getLogger(AdminReportsController.class.getName()).log(Level.SEVERE, null, ex);
       }
       return (ReservationSessionBeanRemote)httpsession.getAttribute("reservationremote");
     }
     // -------------------------------- rooms by service start --------------------------------
    @RequestMapping(value="admin/reports/roomsbyservice",method = RequestMethod.GET)
    public String showRoomsbyServiceReport(Model model){
        model.addAttribute("services", getServiceSessionRemote().getAll());
        return "admin/roomsbyservicereport";
    }
    @RequestMapping(value="admin/reports/roomsbyservice",method=RequestMethod.POST)
    public String searchRoomsByService(Model model,HttpServletRequest request){
        String[] selectedServiceIds = request.getParameterValues("services");
        int[] integerarray  = new int[selectedServiceIds.length];
        for(int x=0;x<selectedServiceIds.length;x++){
            integerarray[x]=Integer.parseInt(selectedServiceIds[x]);
        }
 
        List roomsByServices =getRoomSessionRemote().getRoomsByServiceId(integerarray[0]);
        //List roomsByServices =getRoomSessionRemote().getRoomsByServices(getServiceSessionRemote().getServicesAsCollection(integerarray));
        List selectedServiceObjects = getServiceSessionRemote().getServicesAsCollection(integerarray);
        System.out.println(selectedServiceObjects);
        model.addAttribute("rooms", roomsByServices);
        model.addAttribute("services", getServiceSessionRemote().getAll());
        model.addAttribute("selectedServiceIds",selectedServiceIds);
        model.addAttribute("selectedServiceObjects",selectedServiceObjects);
        return "admin/roomsbyservicereport";
    }
    
    // ------------------- end for rooms by service -----------------------------------
    
    // ------------------- start for room reservation details report ------------------
    
    @RequestMapping(value="admin/reports/reservationdetail", method=RequestMethod.GET)
    public String openReservationDetailReport(Model model){
        return "admin/roomreservationdetail";
    }
    
    @RequestMapping(value="admin/reports/reservationdetail", method=RequestMethod.POST)
    public String searchReservationDetail(Model model,HttpServletRequest request){
        int floor=0, priceMin=0, priceMax=0;
        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
        Date dateFrom=null, dateTo=null;
        List<String> errors = new ArrayList<>();
        List<String> messages = new ArrayList<>();
        try{
            
            dateFrom = dateformat.parse(request.getParameter("from"));
            
            dateTo = dateformat.parse(request.getParameter("to"));
            
            
            if(dateFrom.after(dateTo)){
                errors.add("Date range values should be in ascending order.");
            }
        }catch(ParseException ex){
            errors.add("Invalid date value. Please enter valid date values.");
        }catch(Exception ex){
            errors.add("Invalid date value. Please enter valid date values.");
        }
        
        
        if(!request.getParameter("floor").isEmpty()){
           try{
               floor=Integer.parseInt(request.getParameter("floor"));
           }catch(NumberFormatException ex){
               errors.add("Invalid floor value. Either enter a valid floor or leave it blank.");
           } catch(Exception ex){
               errors.add("Invalid floor vasdfdsflue. Either enter a valid floor or leave it blank.");
           }
        }
        
        try{
            if(!request.getParameter("priceMin").isEmpty() ){
                priceMin = Integer.parseInt(request.getParameter("priceMin"));
                
            }else{
                priceMin = 0;
            }
            if(!request.getParameter("priceMax").isEmpty()){
                priceMax = Integer.parseInt(request.getParameter("priceMax"));
            }else{
                priceMax =0;
            }
            if(priceMin<0){
                errors.add("Invalid minimum price. Price can't be negative");
            }
            if(priceMax<0){
                errors.add("Invalid maximum price. Price can't be negative");
            }
            if(priceMin>priceMax){
                errors.add("Price range values should be in ascending order.");
            }
        }catch(NumberFormatException ex){
            errors.add("Invalid price range. Either enter a valid price or leave it blank.");
        }catch(Exception ex){
            errors.add("Invalid price range. Either enter a valid price or leave it blank.");
        }
         
        if(errors.size()>0) model.addAttribute("errors", errors);
 
        List<Reservation> reservations =  getReservationSessionRemote().findReservations(dateFrom, dateTo, priceMin, priceMax, floor==0?-1:floor);
        System.out.print(dateFrom);
        System.out.print(dateTo);
        model.addAttribute("floor",floor<=0?0:floor);
        model.addAttribute("from",dateFrom==null?"":dateformat.format(dateFrom));
        model.addAttribute("to",dateTo==null?"":dateformat.format(dateTo));
        model.addAttribute("priceMin",priceMin<=0?0:priceMin);
        model.addAttribute("priceMax",priceMax<=0?99999:priceMax);
        
        model.addAttribute("reservations",reservations);
        
        
        return "admin/roomreservationdetail";
    }
    
    
    
    //-------------------- end of room reservation details report ---------------------
    
    // start of message view request
    
    @RequestMapping(value="admin/messages", method=RequestMethod.GET)
    public String showUserRequestedMessages(Model model){
        
        return "admin/specialrequestmessages";
    }
    // end of message view admin request
}
