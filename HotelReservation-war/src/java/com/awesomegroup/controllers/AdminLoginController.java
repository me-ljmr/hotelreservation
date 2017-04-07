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
        model.addAttribute("remember","");
        if(httpsession.getAttribute("remember")!=null ){
            model.addAttribute("remember","checked");
             
            model.addAttribute("login", httpsession.getAttribute("rememberuser"));
            model.addAttribute("password", httpsession.getAttribute("rememberpassword"));
             
        }
         
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
        String remember  = request.getParameter("remember");
         
        if(remember!=null){
            httpsession.setAttribute("remember", "checked");
            httpsession.setAttribute("rememberuser", username);
            httpsession.setAttribute("rememberpassword", password);
            
            
        }else{
            httpsession.removeAttribute("remember");
            httpsession.removeAttribute("rememberuser");
            httpsession.removeAttribute("rememberpassword");
        }
         
        if( "".equals(username) || "".equals(password)){
             if(errors==null)errors = new ArrayList<>();
            errors.add("Login name or password cannot be empty");
            model.addAttribute("errors", errors);
            model.addAttribute("login",username);
            model.addAttribute("password",password);
                return "admin/login";
        }
        try{
             Object obj = getAdminSessionBeanRemote().find(username);
             if(obj==null)
            AdminInfo admin= (AdminInfo)getAdminSessionBeanRemote().find(username);
            
            if(admin==null)
            {
                 if(errors==null)errors = new ArrayList<>();
                errors.add("Not able to connect to session bean");
                model.addAttribute("errors", errors);
                return "admin/login";
            }
            else{
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
            }
        } 
        catch(Exception ex){
            pauth = null;
            if(errors==null)errors = new ArrayList<>();
            if(!ex.getMessage().trim().equals(""))
            errors.add(ex.getMessage());
            else
                errors.add("Error while trying to connect");
            model.addAttribute("errors", errors);
            return "admin/login";
        }
        
    }
}
