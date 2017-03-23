/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.awesomegroup.sessionbean;

import com.awesomegroup.entity.RoomPhotoGallery;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author lujamanandhar
 */
@Local
public interface RoomPhotoGalleryLocal {
    RoomPhotoGallery get(int id);
    List getAllPicturesById(int roomId);
    List getAllPicturesFor(String roomNumber);

    void delete(int id);

    void save(RoomPhotoGallery service);
}
