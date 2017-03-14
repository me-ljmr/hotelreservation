/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.awesomegroup;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author lujamanandhar
 */
@Entity
@Table(name = "room_service")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RoomService.findAll", query = "SELECT r FROM RoomService r"),
    @NamedQuery(name = "RoomService.findById", query = "SELECT r FROM RoomService r WHERE r.id = :id"),
    @NamedQuery(name = "RoomService.findByRoomId", query = "SELECT r FROM RoomService r WHERE r.roomId = :roomId"),
    @NamedQuery(name = "RoomService.findByServiceId", query = "SELECT r FROM RoomService r WHERE r.serviceId = :serviceId")})
public class RoomService implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "room_id")
    private int roomId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "service_id")
    private int serviceId;

    public RoomService() {
    }

    public RoomService(Integer id) {
        this.id = id;
    }

    public RoomService(Integer id, int roomId, int serviceId) {
        this.id = id;
        this.roomId = roomId;
        this.serviceId = serviceId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
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
        if (!(object instanceof RoomService)) {
            return false;
        }
        RoomService other = (RoomService) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.awesomegroup.RoomService[ id=" + id + " ]";
    }
    
}
