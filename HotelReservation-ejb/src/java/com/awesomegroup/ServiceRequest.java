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
@Table(name = "service_request")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ServiceRequest.findAll", query = "SELECT s FROM ServiceRequest s"),
    @NamedQuery(name = "ServiceRequest.findByServiceId", query = "SELECT s FROM ServiceRequest s WHERE s.serviceId = :serviceId"),
    @NamedQuery(name = "ServiceRequest.findByServiceRequest", query = "SELECT s FROM ServiceRequest s WHERE s.serviceRequest = :serviceRequest"),
    @NamedQuery(name = "ServiceRequest.findByRequestedOn", query = "SELECT s FROM ServiceRequest s WHERE s.requestedOn = :requestedOn"),
    @NamedQuery(name = "ServiceRequest.findByIsApproved", query = "SELECT s FROM ServiceRequest s WHERE s.isApproved = :isApproved"),
    @NamedQuery(name = "ServiceRequest.findByAcknowledgement", query = "SELECT s FROM ServiceRequest s WHERE s.acknowledgement = :acknowledgement"),
    @NamedQuery(name = "ServiceRequest.findByUserId", query = "SELECT s FROM ServiceRequest s WHERE s.userId = :userId")})
public class ServiceRequest implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "service_id")
    private Integer serviceId;
    @Size(max = 100)
    @Column(name = "service_request")
    private String serviceRequest;
    @Column(name = "requested_on")
    @Temporal(TemporalType.DATE)
    private Date requestedOn;
    @Column(name = "is_approved")
    private Boolean isApproved;
    @Size(max = 100)
    @Column(name = "acknowledgement")
    private String acknowledgement;
    @Column(name = "user_id")
    private Integer userId;

    public ServiceRequest() {
    }

    public ServiceRequest(Integer serviceId) {
        this.serviceId = serviceId;
    }

    public Integer getServiceId() {
        return serviceId;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }

    public String getServiceRequest() {
        return serviceRequest;
    }

    public void setServiceRequest(String serviceRequest) {
        this.serviceRequest = serviceRequest;
    }

    public Date getRequestedOn() {
        return requestedOn;
    }

    public void setRequestedOn(Date requestedOn) {
        this.requestedOn = requestedOn;
    }

    public Boolean getIsApproved() {
        return isApproved;
    }

    public void setIsApproved(Boolean isApproved) {
        this.isApproved = isApproved;
    }

    public String getAcknowledgement() {
        return acknowledgement;
    }

    public void setAcknowledgement(String acknowledgement) {
        this.acknowledgement = acknowledgement;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (serviceId != null ? serviceId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ServiceRequest)) {
            return false;
        }
        ServiceRequest other = (ServiceRequest) object;
        if ((this.serviceId == null && other.serviceId != null) || (this.serviceId != null && !this.serviceId.equals(other.serviceId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.awesomegroup.ServiceRequest[ serviceId=" + serviceId + " ]";
    }
    
}
