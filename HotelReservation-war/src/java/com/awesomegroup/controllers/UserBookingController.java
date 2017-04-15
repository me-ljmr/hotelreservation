/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.awesomegroup.controllers;

import com.awesomegroup.entity.Reservation;
import com.awesomegroup.entity.Room;
import com.awesomegroup.entity.UserInfo;
import com.awesomegroup.sessionbean.ReservationSessionBeanRemote;
import com.awesomegroup.sessionbean.RoomSessionBeanRemote;
import com.awesomegroup.sessionbean.UserInfoSessionBeanRemote;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserBookingController {

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

    @RequestMapping("booking")
    public String getReservations(Model model) throws ParseException {
        if (httpsession.getAttribute("whoisloggedin") == null) {
            return "redirect:login";
        }
        UserInfo userinfo = (UserInfo) httpsession.getAttribute("whoisloggedin");
        int id = userinfo.getId();
        System.out.print("user id is " + id);
        List<Room> rooms = null;
        List<Reservation> reservations = null;
        try {
            rooms = getRoomSessionRemote().getAll();
            reservations = getReservationSessionRemote().findReservationsByUserId(id);
            model.addAttribute("error", null);

        } catch (Exception ex) {
            model.addAttribute("error", ex.getMessage());
        }
 
        model.addAttribute("rooms", rooms);
        model.addAttribute("userinfo", (UserInfo) httpsession.getAttribute("whoisloggedin"));
        model.addAttribute("reservations", reservations);

        return "booking";
    }
//    @RequestMapping(value = "/searchBooking", method = RequestMethod.GET)
//    public String searchBooking(Model model) {
//       
//        return "booking";
//    }

    @RequestMapping(value = "/booking/new", method = RequestMethod.POST)
    public String newBooking(Model model, HttpServletRequest request, HttpServletResponse response) throws ParseException {
        if (httpsession.getAttribute("whoisloggedin") == null) {
            return "redirect:login";
        }

        UserInfo userinfo = (UserInfo) httpsession.getAttribute("whoisloggedin");
        int id = userinfo.getId();
        userinfo = (UserInfo) getUserInfoSessionRemote().get(id);
        Calendar cal1 = new GregorianCalendar();
        Calendar cal2 = new GregorianCalendar();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");

        String date_from = request.getParameter("date_from");
        String date_till = request.getParameter("date_till");
        String roomseleted = request.getParameter("roomselected");

        Date datef = sdf.parse(date_from);

        Date datet = sdf.parse(date_till);
        Date tdate = new Date();

        Reservation reservation = new Reservation();
        //Room room = (Room) getRoomSessionRemote().get(Integer.parseInt(roomseleted)); 
        Room room = (Room) getRoomSessionRemote().get(34);

        System.out.print("room " + room + "date in: " + datef + "/n" + datet + " " + tdate + " " + userinfo);
//        reservation.setDateFrom(datef);
//        reservation.setDateTill(datet);
//        reservation.setBookedDate(tdate);
//        reservation.setRoomId(room);
       reservation.setUserId(userinfo);
//        reservation.setSpecialService("");
        reservation.setStatus("BK");
        System.out.println("test::  " + reservation.getStatus()+"/n "+
                reservation.getUserId());

        sessionBean = getReservationSessionRemote();
        sessionBean.save(reservation);
        model.addAttribute("userinfo", (UserInfo) httpsession.getAttribute("whoisloggedin"));

        return "booking";
    }

    @RequestMapping(value = "/booking/cancel/{id}", method = RequestMethod.GET)
    public String cancelBooking(Model model, @PathVariable("id") int id) {
        if (httpsession.getAttribute("whoisloggedin") == null) {
            return "redirect:login";
        }
        model.addAttribute("reservation", getReservationSessionRemote().find(id));
//        Reservation reservations = (Reservation) getReservationSessionRemote().find(id);

        model.addAttribute("userinfo", (UserInfo) httpsession.getAttribute("whoisloggedin"));

        //System.err.print(new Date))
        return "bookingcancel";
    }

    @RequestMapping(value = "/booking/cancel/{id}", method = RequestMethod.POST)
    public String updateReservation(Model model, HttpServletRequest request, @PathVariable("id") int id) {
        if (httpsession.getAttribute("whoisloggedin") == null) {
            return "redirect:login";
        }
        String error = null;

        //check if it is BK allow cancel, if not unable
        String status = request.getParameter("status");
        if (!"BK".equals(status)) {
            error = "Unable to cancel";
        }

        Reservation reservation = (Reservation) getReservationSessionRemote().find(id);
        sessionBean = getReservationSessionRemote();
        reservation.setStatus("CC");
        sessionBean.save(reservation);
        model.addAttribute("userinfo", (UserInfo) httpsession.getAttribute("whoisloggedin"));
        model.addAttribute("reservation", "Booking has been cancel successfully.");

        if (error == null) {
            return "redirect:booking";
        } else {
            model.addAttribute("error", error);
            return "booking";
        }

    }

    private RoomSessionBeanRemote getRoomSessionRemote() {

        try {
            ctx = new InitialContext();
            //httpsession.setAttribute("roomremote",null);
            if (httpsession.getAttribute("roomremote") == null) {
                httpsession.setAttribute("roomremote", ctx.lookup(RoomSessionBeanRemote.class.getName()));
            }
        } catch (NamingException ex) {
            Logger.getLogger(RoomController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (RoomSessionBeanRemote) httpsession.getAttribute("roomremote");
    }

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

    public int daysBetween(Date d1, Date d2) {
        return (int) ((d2.getTime() - d1.getTime()) / (1000 * 60 * 60 * 24) + 1);
    }
 
}
