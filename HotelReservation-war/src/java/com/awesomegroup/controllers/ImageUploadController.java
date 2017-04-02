/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.awesomegroup.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.QueryParam;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

/**
 *
 * @author lujamanandhar
 */
@Controller
public class ImageUploadController {
    @RequestMapping(value="upload", method=RequestMethod.GET)
    public String uploaderTest()
    {
        
        return "admin/index";
    }
    @RequestMapping(value="admin/upload", method=RequestMethod.POST)
    public String handleFileUpload(Model model,MultipartHttpServletRequest request,@QueryParam("returnView") String viewname){
            Iterator<String> iterator = request.getFileNames();
            ArrayList<byte[]> images=new ArrayList<>();
            
            while (iterator.hasNext()) {
                try {
                    String fileName = iterator.next();
                    MultipartFile multipartFile = request.getFile(fileName);
                    byte[] image = multipartFile.getBytes();
                     
                    images.add(image);
                    // do stuff...
                } catch (IOException ex) {
                    Logger.getLogger(ImageUploadController.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
            model.addAttribute("images",images);
            
            return viewname;
            

    }    
}
