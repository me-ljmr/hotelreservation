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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author lujamanandhar
 */
@Entity
@Table(name = "room_calendar")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RoomCalendar.findAll", query = "SELECT r FROM RoomCalendar r"),
    @NamedQuery(name = "RoomCalendar.findByRoomCalId", query = "SELECT r FROM RoomCalendar r WHERE r.roomCalId = :roomCalId"),
    @NamedQuery(name = "RoomCalendar.findByDiscountDate", query = "SELECT r FROM RoomCalendar r WHERE r.discountDate = :discountDate"),
    @NamedQuery(name = "RoomCalendar.findByPromotionPercentage", query = "SELECT r FROM RoomCalendar r WHERE r.promotionPercentage = :promotionPercentage")})
public class RoomCalendar implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "room_cal_id")
    private Integer roomCalId;
    @Column(name = "discount_date")
    @Temporal(TemporalType.DATE)
    private Date discountDate;
    @Column(name = "promotion_percentage")
    private Integer promotionPercentage;

    public RoomCalendar() {
    }

    public RoomCalendar(Integer roomCalId) {
        this.roomCalId = roomCalId;
    }

    public Integer getRoomCalId() {
        return roomCalId;
    }

    public void setRoomCalId(Integer roomCalId) {
        this.roomCalId = roomCalId;
    }

    public Date getDiscountDate() {
        return discountDate;
    }

    public void setDiscountDate(Date discountDate) {
        this.discountDate = discountDate;
    }

    public Integer getPromotionPercentage() {
        return promotionPercentage;
    }

    public void setPromotionPercentage(Integer promotionPercentage) {
        this.promotionPercentage = promotionPercentage;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (roomCalId != null ? roomCalId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RoomCalendar)) {
            return false;
        }
        RoomCalendar other = (RoomCalendar) object;
        if ((this.roomCalId == null && other.roomCalId != null) || (this.roomCalId != null && !this.roomCalId.equals(other.roomCalId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.awesomegroup.RoomCalendar[ roomCalId=" + roomCalId + " ]";
    }
    
}
