/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.awesomegroup.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author lujamanandhar
 */
@Controller
public class IndexController {
    @RequestMapping("home")
    public String home(Model model){
        return "index";
    }
}
