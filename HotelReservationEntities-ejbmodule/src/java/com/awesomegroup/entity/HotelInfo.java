package com.awesomegroup.entity;



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
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
    @NamedQuery(name = "HotelInfo.findByContactNumber", query = "SELECT h FROM HotelInfo h WHERE h.contactNumber = :contactNumber"),
    @NamedQuery(name = "HotelInfo.findByEmail", query = "SELECT h FROM HotelInfo h WHERE h.email = :email"),
    @NamedQuery(name = "HotelInfo.findByWebUrl", query = "SELECT h FROM HotelInfo h WHERE h.webUrl = :webUrl"),
    @NamedQuery(name = "HotelInfo.findByAddress", query = "SELECT h FROM HotelInfo h WHERE h.address = :address")})
public class HotelInfo implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Lob
    @Column(name = "logo")
    private byte[] logo;
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
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "contact_number")
    private String contactNumber;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "web_url")
    private String webUrl;
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

    public HotelInfo(Integer id, String title, byte[] logo, String contactNumber, String email, String webUrl) {
        this.id = id;
        this.title = title;
        this.logo = logo;
        this.contactNumber = contactNumber;
        this.email = email;
        this.webUrl = webUrl;
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


    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWebUrl() {
        return webUrl;
    }

    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
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

    public byte[] getLogo() {
        return logo;
    }

    public void setLogo(byte[] logo) {
        this.logo = logo;
    }
    
}
