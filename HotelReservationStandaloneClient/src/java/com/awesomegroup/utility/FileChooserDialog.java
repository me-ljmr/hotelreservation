/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.awesomegroup.utility;

/**
 *
 * @author lujamanandhar
 */
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.apache.commons.io.FileUtils;

public class FileChooserDialog {
  public static void main(String[] args) {
    JFileChooser fileopen = new JFileChooser();
    FileFilter filter = new FileNameExtensionFilter("Image Files",".jpg",".gif",".png");
    fileopen.addChoosableFileFilter(filter);
    fileopen.setMultiSelectionEnabled(true);
    
    int ret = fileopen.showDialog(null, "Open file");

    if (ret == JFileChooser.APPROVE_OPTION) {
        try {
            File[] files = fileopen.getSelectedFiles();
            File file = fileopen.getSelectedFile();
            byte[] filedata = FileUtils.readFileToByteArray(file);
            System.out.println(file);
        } catch (IOException ex) {
            Logger.getLogger(FileChooserDialog.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
  }
  public List<byte[]> getFileFromChooser(){
      JFileChooser fileopen = new JFileChooser();
    FileFilter filter = new FileNameExtensionFilter("Image Files",".jpg",".gif",".png");
    fileopen.addChoosableFileFilter(filter);
    fileopen.setMultiSelectionEnabled(true);
    List<byte[]> filesInBytes=new ArrayList<>();
    int ret = fileopen.showDialog(null, "Open file");

    if (ret == JFileChooser.APPROVE_OPTION) {
        try {
            File[] files = fileopen.getSelectedFiles();
            byte[] filedata=new byte[0];
            //File file = fileopen.getSelectedFile();
            for(File file : files){
                filedata = FileUtils.readFileToByteArray(file);
                filesInBytes.add(filedata);
                //FileUtils.writeByteArrayToFile(file, filedata);
            }
            
        } catch (IOException ex) {
            Logger.getLogger(FileChooserDialog.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    return filesInBytes;
  }
}
