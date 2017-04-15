/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.awesomegroup.controllers;

import com.awesomegroup.entity.UserInfo;
import com.awesomegroup.sessionbean.UserInfoSessionBeanRemote;
import com.awesomegroup.utility.PasswordAuthentication;
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

@Controller
public class UserController {

    @Autowired
    private HttpSession httpsession;

    private InitialContext ctx;
    private UserInfoSessionBeanRemote sessionBean;

    private UserInfoSessionBeanRemote getUserInfoSessionRemote() {

        try {
            ctx = new InitialContext();
            if (httpsession.getAttribute("user") == null) {
                httpsession.setAttribute("user", ctx.lookup(UserInfoSessionBeanRemote.class.getName()));
            }
        } catch (NamingException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (UserInfoSessionBeanRemote) httpsession.getAttribute("user");
    }

    @RequestMapping(value = "/newuser", method = RequestMethod.POST)
    public String saveUser(Model model, HttpServletRequest request, HttpServletResponse response) throws ParseException {
        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        String contactNumber = request.getParameter("contactNumber");
        String address = request.getParameter("address");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String dateStr = request.getParameter("dob");
          //hash. password
        
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
           //     formatter.setLenient(false);
        String msg = "";

//        if(userFirstName.equals("")||userLastName.equals("")||userEmail.equals("")||userPassword.equals("")){
//            //errorTest = "Please enter first name";
//            error.add(errorTest);
//            model.addAttribute("errorTest", errorTest);
//            return "registration";
//            
//        }
//        else if(!userFirstName.matches("[a-zA-Z]")||!userLastName.matches("[a-zA-Z]")){
//            error.add(errorTest2);
//            model.addAttribute("errorTest2", errorTest2);
//            return "registration";
//        }
//        
//        else if(userContactNumber.matches("[a-zA-Z]")){
//            error.add(errorTest3);
//            model.addAttribute("errorTest3", errorTest3);
//            //return "registration";
//            return "registration";
//        }
//        
//        else{
        if(firstname.isEmpty()||lastname.isEmpty()||email.isEmpty()
                ||address.isEmpty()||contactNumber.isEmpty()
                ||password.isEmpty()||dateStr.isEmpty()){
            msg="The fields can not be empty";
            model.addAttribute("msg", msg);
        } 

//        if (formatter.isLenient() == false) {
//            msg = "Invalid date of Birth";
//            model.addAttribute("msg", msg);
//        } 
        else{
            
       
        UserInfo userInfo = new UserInfo();
        
        userInfo.setFirstName(firstname);
        userInfo.setLastName(lastname);
        userInfo.setContactNumber(contactNumber);

        try{
           Date DoB = formatter.parse(dateStr);
           userInfo.setDateOfBirth(DoB);
           System.out.print(DoB +""+ formatter.parse(dateStr));
        }catch(ParseException ex){
            msg = "Invalid date of Birth";
            model.addAttribute("msg", msg);
        }
        
        userInfo.setEmail(email);
        userInfo.setPassword(password);
        userInfo.setUserAddress(address);
         
        sessionBean = getUserInfoSessionRemote();
        sessionBean.save(userInfo);
         }
        
        return "login";
    }

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String showlogin(Model model) {
        model.addAttribute("remember", "");
        if (httpsession.getAttribute("remember") != null) {
            model.addAttribute("remember", "checked");
            model.addAttribute("email", httpsession.getAttribute("rememberuser"));
            model.addAttribute("password", httpsession.getAttribute("rememberpassword"));
        }

        return "login";
    }

    @RequestMapping(value = "logout")
    public String logout() {
        httpsession.setAttribute("whoisloggedin", null);
        httpsession.removeAttribute("whoisloggedin");
        return "redirect:login";
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String logmein(Model model, HttpServletRequest request) {
        List<String> errors = null;
        PasswordAuthentication pauth = new PasswordAuthentication();

        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String remember = request.getParameter("remember");
        //pauth.hash(password);
        if (remember != null) {
            httpsession.setAttribute("remember", "checked");
            httpsession.setAttribute("rememberuser", email);
            httpsession.setAttribute("rememberpassword", password);

        } else {
            httpsession.removeAttribute("remember");
            httpsession.removeAttribute("rememberuser");
            httpsession.removeAttribute("rememberpassword");
        }

        if ("".equals(email) || "".equals(password)) {
            if (errors == null) {
                errors = new ArrayList<>();
            }
            errors.add("Login name or password cannot be empty");
            model.addAttribute("errors", errors);
            model.addAttribute("email", email);
            model.addAttribute("password", password);
            return "login";
        }
        try {
            System.out.print(getUserInfoSessionRemote().getByUserEmail(email));
            UserInfo user = (UserInfo) getUserInfoSessionRemote().getByUserEmail(email);
            System.out.print(user);
            if (user == null) {
                if (errors == null) {
                    errors = new ArrayList<>();
                }
                errors.add("Not able to connect to session bean");
                model.addAttribute("errors", errors);
                return "login";
            } else {
                if (pauth.authenticate(password, user.getPassword())) {
                    pauth = null;
                    httpsession.setAttribute("whoisloggedin", user);
                    return "redirect:booking";
                } else {
                    pauth = null;
                    if (errors == null) {
                        errors = new ArrayList<>();
                    }
                    errors.add("Login name and password doesn't match");

                    model.addAttribute("email", email);
                    model.addAttribute("password", password);

                    model.addAttribute("errors", errors);
                    return "login";
                }
            }
        } catch (Exception ex) {
            pauth = null;
            if (errors == null) {
                errors = new ArrayList<>();
            }
            if (!ex.getMessage().trim().equals("")) {
                errors.add(ex.getMessage());
            } else {
                errors.add("Error while trying to connect");
            }
            model.addAttribute("errors", errors);
            return "login";
        }
    }

}
