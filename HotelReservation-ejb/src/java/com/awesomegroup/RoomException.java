/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.awesomegroup;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author lujamanandhar
 */
@Entity
@Table(name = "room_exception")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RoomException.findAll", query = "SELECT r FROM RoomException r"),
    @NamedQuery(name = "RoomException.findByRoomId", query = "SELECT r FROM RoomException r WHERE r.roomId = :roomId"),
    @NamedQuery(name = "RoomException.findByDemandDate", query = "SELECT r FROM RoomException r WHERE r.demandDate = :demandDate"),
    @NamedQuery(name = "RoomException.findByUserId", query = "SELECT r FROM RoomException r WHERE r.userId = :userId"),
    @NamedQuery(name = "RoomException.findByDescription", query = "SELECT r FROM RoomException r WHERE r.description = :description")})
public class RoomException implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "exception_id")
    private Integer exceptionId;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "room_id")
    private Integer roomId;
    @Column(name = "demand_date")
    @Temporal(TemporalType.DATE)
    private Date demandDate;
    @Column(name = "user_id")
    private Integer userId;
    @Size(max = 100)
    @Column(name = "description")
    private String description;

    public RoomException() {
    }

    public RoomException(Integer roomId) {
        this.roomId = roomId;
    }

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    public Date getDemandDate() {
        return demandDate;
    }

    public void setDemandDate(Date demandDate) {
        this.demandDate = demandDate;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (roomId != null ? roomId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RoomException)) {
            return false;
        }
        RoomException other = (RoomException) object;
        if ((this.roomId == null && other.roomId != null) || (this.roomId != null && !this.roomId.equals(other.roomId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.awesomegroup.RoomException[ roomId=" + roomId + " ]";
    }
 

    public Integer getExceptionId() {
        return exceptionId;
    }

    public void setExceptionId(Integer exceptionId) {
        this.exceptionId = exceptionId;
    }

      
}
