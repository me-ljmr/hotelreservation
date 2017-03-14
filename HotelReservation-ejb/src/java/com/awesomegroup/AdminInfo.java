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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author lujamanandhar
 */
@Entity
@Table(name = "admin_info")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AdminInfo.findAll", query = "SELECT a FROM AdminInfo a"),
    @NamedQuery(name = "AdminInfo.findById", query = "SELECT a FROM AdminInfo a WHERE a.id = :id"),
    @NamedQuery(name = "AdminInfo.findByLoginName", query = "SELECT a FROM AdminInfo a WHERE a.loginName = :loginName"),
    @NamedQuery(name = "AdminInfo.findByLoginPassword", query = "SELECT a FROM AdminInfo a WHERE a.loginPassword = :loginPassword"),
    @NamedQuery(name = "AdminInfo.findByDisplayName", query = "SELECT a FROM AdminInfo a WHERE a.displayName = :displayName")})
public class AdminInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "login_name")
    private String loginName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "login_password")
    private String loginPassword;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "display_name")
    private String displayName;

    public AdminInfo() {
    }

    public AdminInfo(Integer id) {
        this.id = id;
    }

    public AdminInfo(Integer id, String loginName, String loginPassword, String displayName) {
        this.id = id;
        this.loginName = loginName;
        this.loginPassword = loginPassword;
        this.displayName = displayName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getLoginPassword() {
        return loginPassword;
    }

    public void setLoginPassword(String loginPassword) {
        this.loginPassword = loginPassword;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
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
        if (!(object instanceof AdminInfo)) {
            return false;
        }
        AdminInfo other = (AdminInfo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.awesomegroup.AdminInfo[ id=" + id + " ]";
    }
    
}
