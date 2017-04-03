/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.awesomegroup.controllers;

import com.awesomegroup.entity.AdminInfo;
import com.awesomegroup.sessionbean.AdminSessionBeanRemote;
import com.awesomegroup.utility.PasswordAuthentication;
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
public class AdminLoginController {
    
    @Autowired
    private ServletContext sc;
    
    @Autowired
    private HttpSession httpsession;
    
    private InitialContext ctx;
    private AdminSessionBeanRemote sessionBean;
    
     private AdminSessionBeanRemote getAdminSessionBeanRemote(){

       try {
           ctx = new InitialContext();
            
           if (httpsession.getAttribute("adminsessionremote")==null){
               httpsession.setAttribute("adminsessionremote",ctx.lookup(AdminSessionBeanRemote.class.getName()));
           }
       } catch (NamingException ex) {
           Logger.getLogger(AdminLoginController.class.getName()).log(Level.SEVERE, null, ex);
       }
       return (AdminSessionBeanRemote)httpsession.getAttribute("adminsessionremote");
   }
    
    @RequestMapping(value="admin/login", method=RequestMethod.GET)
    public String showlogin(Model model){
        
        
        return "admin/login";
    }
    @RequestMapping(value="admin/logout")
    public String logout(){
        httpsession.setAttribute("whoisloggedinasadmin", null);
        httpsession.removeAttribute("whoisloggedinasadmin");
        return "redirect:login";
    }
    @RequestMapping(value="admin/login",method=RequestMethod.POST)
    public String logmein(Model model, HttpServletRequest request){
        List<String> errors = null;
        PasswordAuthentication pauth = new PasswordAuthentication();
        String username=request.getParameter("login");
        String password = request.getParameter("password");
        
        try{
            AdminInfo admin= (AdminInfo)getAdminSessionBeanRemote().find(username);
            
            
            if(pauth.authenticate(password, admin.getLoginPassword())){
                pauth=null;
                httpsession.setAttribute("whoisloggedinasadmin", admin);
                return "redirect:dashboard";
            }else{
                pauth=null;
                if(errors==null)errors = new ArrayList<>();
                errors.add("Login name and password doesn't match");
                model.addAttribute("login",username);
                model.addAttribute("password",password);
                
                model.addAttribute("errors", errors);
                return "admin/login";
            }
        }catch(Exception ex){
            pauth = null;
            if(errors==null)errors = new ArrayList<>();
            errors.add(ex.getLocalizedMessage());
            model.addAttribute("errors", errors);
            return "admin/login";
        }
        
    }
}
