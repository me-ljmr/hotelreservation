/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.awesomegroup.entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author media
 */
@Entity
@Table(name = "room")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Room.findAll", query = "SELECT r FROM Room r"),
    @NamedQuery(name = "Room.findById", query = "SELECT r FROM Room r WHERE r.id = :id"),
    @NamedQuery(name = "Room.findByFloor", query = "SELECT r FROM Room r WHERE r.floor = :floor"),
    @NamedQuery(name = "Room.findByRoomNumber", query = "SELECT r FROM Room r WHERE r.roomNumber = :roomNumber")})
public class Room implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "floor")
    private Integer floor;
    @Size(max = 10)
    @Column(name = "room_number")
    private String roomNumber;
    @JoinColumn(name = "room_type_id", referencedColumnName = "id")
    @ManyToOne
    private RoomType roomTypeId;
    @JoinColumn(name = "room_photo_gallery_id", referencedColumnName = "id")
    @ManyToOne
    private RoomPhotoGallery roomPhotoGalleryId;
    @OneToMany(mappedBy = "roomId")
    private Collection<RoomPhotoGallery> roomPhotoGalleryCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "roomId")
    private Collection<RoomService> roomServiceCollection;

    public Room() {
    }

    public Room(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFloor() {
        return floor;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public RoomType getRoomTypeId() {
        return roomTypeId;
    }

    public void setRoomTypeId(RoomType roomTypeId) {
        this.roomTypeId = roomTypeId;
    }

    public RoomPhotoGallery getRoomPhotoGalleryId() {
        return roomPhotoGalleryId;
    }

    public void setRoomPhotoGalleryId(RoomPhotoGallery roomPhotoGalleryId) {
        this.roomPhotoGalleryId = roomPhotoGalleryId;
    }

    @XmlTransient
    public Collection<RoomPhotoGallery> getRoomPhotoGalleryCollection() {
        return roomPhotoGalleryCollection;
    }

    public void setRoomPhotoGalleryCollection(Collection<RoomPhotoGallery> roomPhotoGalleryCollection) {
        this.roomPhotoGalleryCollection = roomPhotoGalleryCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    public void setRoomServiceCollection(Collection<RoomService> roomServiceCollection) {
        this.roomServiceCollection = roomServiceCollection;
    }
    
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Room)) {
            return false;
        }
        Room other = (Room) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.awesomegroup.entity.Room[ id=" + id + " ]";
    }
    
}
