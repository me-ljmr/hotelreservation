/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.awesomegroup.controllers;

import com.awesomegroup.entity.UserInfo;
import com.awesomegroup.sessionbean.UserInfoSessionBeanRemote;
import java.text.DateFormat;
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
public class UserController {
    
    
    @Autowired
    private HttpSession httpsession;
    
    private InitialContext ctx;
    private UserInfoSessionBeanRemote sessionBean;
    private UserInfoSessionBeanRemote getUserSessionRemote(){
        try {
            ctx = new InitialContext();
            if (httpsession.getAttribute("user")==null){
                httpsession.setAttribute("user",ctx.lookup(UserInfoSessionBeanRemote.class.getName()));
            }
        } catch (NamingException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (UserInfoSessionBeanRemote)httpsession.getAttribute("user");
    }
    private UserInfoSessionBeanRemote getUserInfoSessionRemote(){
        
        try {
            ctx = new InitialContext();
            if (httpsession.getAttribute("user")==null){
                httpsession.setAttribute("user",ctx.lookup(UserInfoSessionBeanRemote.class.getName()));
            }
        } catch (NamingException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (UserInfoSessionBeanRemote)httpsession.getAttribute("user");
    }
    
    
    @RequestMapping(value="/user/new", method=RequestMethod.GET)
    public String newUser(Model model){
        
        model.addAttribute("user",getUserSessionRemote().getAll());
        model.addAttribute("user",new UserInfo());
        model.addAttribute("user", getUserSessionRemote().getAll());   
        return "registration";
    }
    
    @RequestMapping(value="/user/new/save" , method=RequestMethod.POST)
    public String saveUser(Model model, HttpServletRequest request, HttpServletResponse response) throws ParseException{
        String userFirstName = request.getParameter("firstName");
        String userLastName = request.getParameter("lastName");
        String userContactNumber=request.getParameter("contactNumber");
        String userAddress = request.getParameter("address");
        String userEmail = request.getParameter("email");
        String userPassword = request.getParameter("password");
        
        List<String> error=new ArrayList<String>();
        String errorTest="Please enter the details";
        String errorTest2 = "Please enter only letters";
        String errorTest3 = "Please enter only numbers";
       
        //model.addAttribute("error", errorTest);
        if(userFirstName.equals("")||userLastName.equals("")||userEmail.equals("")||userPassword.equals("")){
            //errorTest = "Please enter first name";
            error.add(errorTest);
            model.addAttribute("errorTest", errorTest);
            return "registration";
            
        }
        else if(!userFirstName.matches("[a-zA-Z]")||!userLastName.matches("[a-zA-Z]")){
            error.add(errorTest2);
            model.addAttribute("errorTest2", errorTest2);
            return "registration";
        }
        
        else if(userContactNumber.matches("[a-zA-Z]")){
            error.add(errorTest3);
            model.addAttribute("errorTest3", errorTest3);
            //return "registration";
            return "registration";
        }
        
        else{
        
        UserInfo userInfo = new UserInfo();
        userInfo.setFirstName(userFirstName);
        userInfo.setLastName(userLastName);
        userInfo.setContactNumber(userContactNumber);
        String dateStr = request.getParameter("dob"); 
        DateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
        
        Date startDate = formatter.parse(dateStr);
       
        userInfo.setDateOfBirth(startDate);
        userInfo.setEmail(userEmail);
        userInfo.setPassword(userPassword);
        userInfo.setUserAddress(userAddress);
        
        sessionBean = getUserInfoSessionRemote();
        sessionBean.save(userInfo);
        model.addAttribute("Saved user", "User with name: " + userFirstName+" " +userLastName+ 
                " has been saved successfully.");
        model.addAttribute("users",getUserInfoSessionRemote().getAll());
        
        return "registration";
        }
        
        
    }
}
