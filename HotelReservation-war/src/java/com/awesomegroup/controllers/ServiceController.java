/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.awesomegroup.controllers;

import com.awesomegroup.entity.AdminInfo;
import com.awesomegroup.entity.Service;
import com.awesomegroup.sessionbean.ServiceSessionBeanRemote;
import java.util.ArrayList;
import java.util.List;
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
public class ServiceController {
    @Autowired
    private ServletContext sc;
    
    @Autowired
    private HttpSession httpsession;
    
    private InitialContext ctx;
    private ServiceSessionBeanRemote sessionBean;
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
    
       
    @RequestMapping("admin/services")
    public String getServices(Model model){
        if (httpsession.getAttribute("whoisloggedinasadmin")==null){
            return "redirect:login";
        }
        List<String> errors = new ArrayList<>(); 
        List<Service> services=null;
        try{
            services= getServiceSessionRemote().getAll();
            
        }catch(Exception ex){
            
            errors.add(ex.getMessage());
        }
        model.addAttribute("errors", errors);
        model.addAttribute("services",services);  
        model.addAttribute("admininfo",(AdminInfo)httpsession.getAttribute("whoisloggedinasadmin"));
        return "admin/services";
    }
    private boolean emptyCheck(String checkstr){
        return checkstr.isEmpty() || checkstr==null || checkstr.trim().equals("");
    }
    
    @RequestMapping(value="admin/services",method=RequestMethod.POST)
    public String servicepost(Model model, HttpServletRequest request){
        if (httpsession.getAttribute("whoisloggedinasadmin")==null){
            return "redirect:login";
        }
        List<String> errors = new ArrayList<>(); 
        List<String> messages = new ArrayList<>();
        String title,desc;
        if(request.getParameter("mode").equals("save")){
            // new or edit
            title = request.getParameter("title");
            desc = request.getParameter("description");
            if(emptyCheck(request.getParameter("title"))){
                 
                errors.add("Title cannot be empty");
            }
            if(errors==null)
            {
                Service service=null;
                if(request.getParameter("serviceid").equals("0")){
                    // new 
                     service = new Service();
                     service.setId(0);
                    

                }else{
                    //edit
                    service = (Service)getServiceSessionRemote().get(Integer.parseInt(request.getParameter("serviceid")));
                     
                }
                service.setTitle(request.getParameter("title"));
                service.setDescription(request.getParameter("description"));
                getServiceSessionRemote().save(service);
                 
                messages.add("Data Saved Successfully");
            }else{
                model.addAttribute("title",title);
                model.addAttribute("description", desc);
            }
        }else if (request.getParameter("mode").equals("delete")){
            // delete
            if (!emptyCheck(request.getParameter("serviceid"))){
                getServiceSessionRemote().delete(Integer.parseInt(request.getParameter("serviceid")));
                 
                messages.add("Data deleted successfully");
            }else{
                 
                errors.add("Invalid service identification number");
            }
        }
        
        List<Service> services=null;
        try{
            services= getServiceSessionRemote().getAll();
             
        }catch(Exception ex){
             
            errors.add(ex.getMessage());
        }
        if (errors.isEmpty()) errors = null;
        if(messages.isEmpty()) messages =null;
        model.addAttribute("admininfo",(AdminInfo)httpsession.getAttribute("whoisloggedinasadmin"));
        model.addAttribute("messages",messages);
        model.addAttribute("errors", errors);
        model.addAttribute("services",services); 
        return "admin/services";
    }
}
