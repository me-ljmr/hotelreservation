package com.awesomegroup.entity;



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
 * @author lujamanandhar
 */
@Entity
@Table(name = "room")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Room.findAll", query = "SELECT r FROM Room r"),
    @NamedQuery(name = "Room.findById", query = "SELECT r FROM Room r WHERE r.id = :id"),
    @NamedQuery(name = "Room.findByFloor", query = "SELECT r FROM Room r WHERE r.floor = :floor"),
    @NamedQuery(name = "Room.findByRoomType", query = "SELECT r FROM Room r WHERE r.roomTypeId.id = :roomTypeId"),
    @NamedQuery(name = "Room.findByRoomNumber", query = "SELECT r FROM Room r WHERE r.roomNumber = :roomNumber"),
    @NamedQuery(name = "Room.findByServiceId", query = "SELECT r FROM Room r INNER JOIN r.roomServiceCollection s where s.serviceId.id = :serviceId"),
        @NamedQuery(name = "Room.findByServices", query = "SELECT r FROM Room r INNER JOIN r.roomServiceCollection s where s.serviceId.id in :services")
})

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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "roomId", fetch = FetchType.EAGER)
    private Collection<RoomService> roomServiceCollection;
    @OneToMany(mappedBy = "roomId")
    private Collection<Reservation> reservationCollection;
    @JoinColumn(name = "room_type_id", referencedColumnName = "id")
    @ManyToOne()
    private RoomType roomTypeId;
    @OneToMany(mappedBy = "roomId")
    private Collection<RoomPhotoGallery> roomPhotoGalleryCollection;

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

    @XmlTransient
    public Collection<RoomService> getRoomServiceCollection() {
        return roomServiceCollection;
    }

    public void setRoomServiceCollection(Collection<RoomService> roomServiceCollection) {
        this.roomServiceCollection = roomServiceCollection;
    }

    @XmlTransient
    public Collection<Reservation> getReservationCollection() {
        return reservationCollection;
    }

    public void setReservationCollection(Collection<Reservation> reservationCollection) {
        this.reservationCollection = reservationCollection;
    }

    public RoomType getRoomTypeId() {
        return roomTypeId;
    }

    public void setRoomTypeId(RoomType roomTypeId) {
        this.roomTypeId = roomTypeId;
    }

    @XmlTransient
    public Collection<RoomPhotoGallery> getRoomPhotoGalleryCollection() {
        return roomPhotoGalleryCollection;
    }

    public void addAPicture(RoomPhotoGallery roompicgallery){
        roomPhotoGalleryCollection.add(roompicgallery);
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
        return "com.awesomegroup.Room[ id=" + id + " ]";
    }
    
}
