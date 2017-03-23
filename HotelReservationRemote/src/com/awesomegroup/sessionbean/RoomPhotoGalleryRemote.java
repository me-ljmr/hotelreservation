/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.awesomegroup.sessionbean;

import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author lujamanandhar
 */
@Remote
public interface RoomPhotoGalleryRemote {

    
    Object get(int id);
    List getAllPicturesById(int roomId);
    List getAllPicturesFor(String roomNumber);

    void delete(int id);

    void save(Object service);
}
