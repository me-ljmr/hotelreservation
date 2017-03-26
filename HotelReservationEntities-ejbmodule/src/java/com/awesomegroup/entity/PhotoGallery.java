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
@Table(name = "photo_gallery")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PhotoGallery.findAll", query = "SELECT p FROM PhotoGallery p"),
    @NamedQuery(name = "PhotoGallery.findById", query = "SELECT p FROM PhotoGallery p WHERE p.id = :id"),
    @NamedQuery(name = "PhotoGallery.findByPhotoTitle", query = "SELECT p FROM PhotoGallery p WHERE p.photoTitle = :photoTitle")})
public class PhotoGallery implements Serializable {
    @Lob
    @Column(name = "photo")
    private byte[] photo;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 20)
    @Column(name = "photo_title")
    private String photoTitle;
    @JoinColumn(name = "hotel_id", referencedColumnName = "id")
    @ManyToOne
    private HotelInfo hotelId;

    public PhotoGallery() {
    }

    public PhotoGallery(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getPhotoTitle() {
        return photoTitle;
    }

    public void setPhotoTitle(String photoTitle) {
        this.photoTitle = photoTitle;
    }

    public HotelInfo getHotelId() {
        return hotelId;
    }

    public void setHotelId(HotelInfo hotelId) {
        this.hotelId = hotelId;
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
        if (!(object instanceof PhotoGallery)) {
            return false;
        }
        PhotoGallery other = (PhotoGallery) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.awesomegroup.PhotoGallery[ id=" + id + " ]";
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }
    
}
