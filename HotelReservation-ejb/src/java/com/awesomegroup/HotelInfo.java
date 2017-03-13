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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author lujamanandhar
 */
@Entity
@Table(name = "hotel_info")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "HotelInfo.findAll", query = "SELECT h FROM HotelInfo h"),
    @NamedQuery(name = "HotelInfo.findByHotelId", query = "SELECT h FROM HotelInfo h WHERE h.hotelId = :hotelId"),
    @NamedQuery(name = "HotelInfo.findByHotelName", query = "SELECT h FROM HotelInfo h WHERE h.hotelName = :hotelName"),
    @NamedQuery(name = "HotelInfo.findByHotelAddress", query = "SELECT h FROM HotelInfo h WHERE h.hotelAddress = :hotelAddress")})
public class HotelInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "hotel_id")
    private Integer hotelId;
    @Size(max = 20)
    @Column(name = "hotel_name")
    private String hotelName;
    @Size(max = 100)
    @Column(name = "hotel_address")
    private String hotelAddress;

    public HotelInfo() {
    }

    public HotelInfo(Integer hotelId) {
        this.hotelId = hotelId;
    }

    public Integer getHotelId() {
        return hotelId;
    }

    public void setHotelId(Integer hotelId) {
        this.hotelId = hotelId;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getHotelAddress() {
        return hotelAddress;
    }

    public void setHotelAddress(String hotelAddress) {
        this.hotelAddress = hotelAddress;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (hotelId != null ? hotelId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HotelInfo)) {
            return false;
        }
        HotelInfo other = (HotelInfo) object;
        if ((this.hotelId == null && other.hotelId != null) || (this.hotelId != null && !this.hotelId.equals(other.hotelId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.awesomegroup.HotelInfo[ hotelId=" + hotelId + " ]";
    }
    
}
