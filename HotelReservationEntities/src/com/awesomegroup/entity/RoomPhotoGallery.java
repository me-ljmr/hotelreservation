package com.awesomegroup.entity;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author lujamanandhar
 */
@Entity
@Table(name = "room_photo_gallery")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RoomPhotoGallery.findAll", query = "SELECT r FROM RoomPhotoGallery r"),
    @NamedQuery(name = "RoomPhotoGallery.findById", query = "SELECT r FROM RoomPhotoGallery r WHERE r.id = :id"),
    @NamedQuery(name = "RoomPhotoGallery.findByRoomNumber", query = "SELECT r FROM RoomPhotoGallery r WHERE r.room.roomNumber = :roomNumber"),
    @NamedQuery(name = "RoomPhotoGallery.findByRoomId", query = "SELECT r FROM RoomPhotoGallery r WHERE r.room_id = :roomId"),
    @NamedQuery(name = "RoomPhotoGallery.findByPhotoTitle", query = "SELECT r FROM RoomPhotoGallery r WHERE r.photoTitle = :photoTitle")})
public class RoomPhotoGallery implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Lob
    @Column(name = "photo")
    private byte[] photo;
    @Size(max = 20)
    @Column(name = "photo_title")
    private String photoTitle;
    @JoinColumn(name = "room_id", referencedColumnName = "id")
    @ManyToOne
    private Room room;

    public RoomPhotoGallery() {
    }

    public RoomPhotoGallery(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public String getPhotoTitle() {
        return photoTitle;
    }

    public void setPhotoTitle(String photoTitle) {
        this.photoTitle = photoTitle;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RoomPhotoGallery)) {
            return false;
        }
        RoomPhotoGallery other = (RoomPhotoGallery) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.awesomegroup.RoomPhotoGallery[ id=" + id + " ]";
    }
    
}
