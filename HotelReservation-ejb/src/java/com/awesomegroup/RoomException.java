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
import javax.validation.constraints.NotNull;
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
    @NamedQuery(name = "RoomException.findById", query = "SELECT r FROM RoomException r WHERE r.id = :id"),
    @NamedQuery(name = "RoomException.findByOnDate", query = "SELECT r FROM RoomException r WHERE r.onDate = :onDate"),
    @NamedQuery(name = "RoomException.findByUserId", query = "SELECT r FROM RoomException r WHERE r.userId = :userId"),
    @NamedQuery(name = "RoomException.findByNotes", query = "SELECT r FROM RoomException r WHERE r.notes = :notes"),
    @NamedQuery(name = "RoomException.findByRoomId", query = "SELECT r FROM RoomException r WHERE r.roomId = :roomId")})
public class RoomException implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "on_date")
    @Temporal(TemporalType.DATE)
    private Date onDate;
    @Column(name = "user_id")
    private Integer userId;
    @Size(max = 100)
    @Column(name = "notes")
    private String notes;
    @Basic(optional = false)
    @NotNull
    @Column(name = "room_id")
    private int roomId;

    public RoomException() {
    }

    public RoomException(Integer id) {
        this.id = id;
    }

    public RoomException(Integer id, int roomId) {
        this.id = id;
        this.roomId = roomId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getOnDate() {
        return onDate;
    }

    public void setOnDate(Date onDate) {
        this.onDate = onDate;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
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
        if (!(object instanceof RoomException)) {
            return false;
        }
        RoomException other = (RoomException) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.awesomegroup.RoomException[ id=" + id + " ]";
    }
    
}
