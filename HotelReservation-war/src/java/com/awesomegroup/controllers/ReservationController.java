/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.awesomegroup.controllers;

import com.awesomegroup.entity.Reservation;
import com.awesomegroup.entity.Room;
import com.awesomegroup.entity.RoomPhotoGallery;
import com.awesomegroup.sessionbean.ReservationSessionBeanRemote;
import com.awesomegroup.sessionbean.RoomPhotoGalleryRemote;
import com.awesomegroup.sessionbean.RoomSessionBeanRemote;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ReservationController {

    @Autowired
    private ServletContext sc;

    @Autowired
    private HttpSession httpsession;

    private InitialContext ctx;
    private ReservationSessionBeanRemote sessionBean;

    private ReservationSessionBeanRemote getReservationSessionRemote() {
        try {
            ctx = new InitialContext();
            if (httpsession.getAttribute("reservationremote") == null) {
                httpsession.setAttribute("reservationremote", ctx.lookup(ReservationSessionBeanRemote.class.getName()));
            }
        } catch (NamingException ex) {
            Logger.getLogger(ReservationController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (ReservationSessionBeanRemote) httpsession.getAttribute("reservationremote");
    }
    private RoomPhotoGalleryRemote getRoomPhotoGalleryRemote(){
          try {
            ctx = new InitialContext();
            if (httpsession.getAttribute("photoremote") == null) {
                httpsession.setAttribute("photoremote", ctx.lookup(RoomPhotoGalleryRemote.class.getName()));
            }
        } catch (NamingException ex) {
            Logger.getLogger(ReservationController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (RoomPhotoGalleryRemote) httpsession.getAttribute("photoremote");
    }
    private RoomSessionBeanRemote getRoomSessionRemote() {

        try {
            ctx = new InitialContext();
            if (httpsession.getAttribute("roomremote") == null) {
                httpsession.setAttribute("roomremote", ctx.lookup(RoomSessionBeanRemote.class.getName()));
            }
        } catch (NamingException ex) {
            Logger.getLogger(ReservationController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (RoomSessionBeanRemote) httpsession.getAttribute("roomremote");
    }

    @RequestMapping("reservation")
    public String getRooms(Model model) {
        List<Room> rooms = null;
        int id = 0;
        try {
            rooms = getRoomSessionRemote().getAll();
            model.addAttribute("error", null);
        } catch (Exception ex) {
            model.addAttribute("error", ex.getMessage());
        }
        model.addAttribute("rooms", rooms);
        return "reservation";
    }

}
