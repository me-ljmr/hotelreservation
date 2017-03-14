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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "reservation")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Reservation.findAll", query = "SELECT r FROM Reservation r"),
    @NamedQuery(name = "Reservation.findById", query = "SELECT r FROM Reservation r WHERE r.id = :id"),
    @NamedQuery(name = "Reservation.findByBookedDate", query = "SELECT r FROM Reservation r WHERE r.bookedDate = :bookedDate"),
    @NamedQuery(name = "Reservation.findByDateFrom", query = "SELECT r FROM Reservation r WHERE r.dateFrom = :dateFrom"),
    @NamedQuery(name = "Reservation.findByDateTill", query = "SELECT r FROM Reservation r WHERE r.dateTill = :dateTill"),
    @NamedQuery(name = "Reservation.findBySpecialService", query = "SELECT r FROM Reservation r WHERE r.specialService = :specialService")})
public class Reservation implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "booked_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date bookedDate;
    @Column(name = "date_from")
    @Temporal(TemporalType.DATE)
    private Date dateFrom;
    @Column(name = "date_till")
    @Temporal(TemporalType.DATE)
    private Date dateTill;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "special_service")
    private String specialService;
    @JoinColumn(name = "room_id", referencedColumnName = "id")
    @ManyToOne
    private Room roomId;
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne
    private UserInfo userId;

    public Reservation() {
    }

    public Reservation(Integer id) {
        this.id = id;
    }

    public Reservation(Integer id, Date bookedDate, String specialService) {
        this.id = id;
        this.bookedDate = bookedDate;
        this.specialService = specialService;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getBookedDate() {
        return bookedDate;
    }

    public void setBookedDate(Date bookedDate) {
        this.bookedDate = bookedDate;
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public Date getDateTill() {
        return dateTill;
    }

    public void setDateTill(Date dateTill) {
        this.dateTill = dateTill;
    }

    public String getSpecialService() {
        return specialService;
    }

    public void setSpecialService(String specialService) {
        this.specialService = specialService;
    }

    public Room getRoomId() {
        return roomId;
    }

    public void setRoomId(Room roomId) {
        this.roomId = roomId;
    }

    public UserInfo getUserId() {
        return userId;
    }

    public void setUserId(UserInfo userId) {
        this.userId = userId;
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
        if (!(object instanceof Reservation)) {
            return false;
        }
        Reservation other = (Reservation) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.awesomegroup.Reservation[ id=" + id + " ]";
    }
    
}
