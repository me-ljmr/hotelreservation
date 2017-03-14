/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.awesomegroup;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author lujamanandhar
 */
@Entity
@Table(name = "hotel_info")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "HotelInfo.findAll", query = "SELECT h FROM HotelInfo h"),
    @NamedQuery(name = "HotelInfo.findById", query = "SELECT h FROM HotelInfo h WHERE h.id = :id"),
    @NamedQuery(name = "HotelInfo.findByTitle", query = "SELECT h FROM HotelInfo h WHERE h.title = :title"),
    @NamedQuery(name = "HotelInfo.findByAddress", query = "SELECT h FROM HotelInfo h WHERE h.address = :address")})
public class HotelInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "title")
    private String title;
    @Size(max = 100)
    @Column(name = "address")
    private String address;
    @OneToMany(mappedBy = "hotelId")
    private Collection<PhotoGallery> photoGalleryCollection;

    public HotelInfo() {
    }

    public HotelInfo(Integer id) {
        this.id = id;
    }

    public HotelInfo(Integer id, String title) {
        this.id = id;
        this.title = title;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @XmlTransient
    public Collection<PhotoGallery> getPhotoGalleryCollection() {
        return photoGalleryCollection;
    }

    public void setPhotoGalleryCollection(Collection<PhotoGallery> photoGalleryCollection) {
        this.photoGalleryCollection = photoGalleryCollection;
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
        if (!(object instanceof HotelInfo)) {
            return false;
        }
        HotelInfo other = (HotelInfo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.awesomegroup.HotelInfo[ id=" + id + " ]";
    }
    
}
