package com.kastourik12.urlshortener.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.AbstractAuditable;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.lang.Nullable;

import javax.persistence.EntityListeners;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import java.util.Date;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class Auditable {
    @CreatedDate
    private Date createdAt;
    @LastModifiedDate
    private Date lastModifiedAt;
    @ManyToOne
    @JoinColumn(name = "created_by_id")
    @CreatedBy
    @Nullable @JsonIgnore
    private User createdBy;

     public Date getCreatedAt() {
         return createdAt;
     }

     public void setCreatedAt(Date createdAt) {
         this.createdAt = createdAt;
     }

     public Date getLastModifiedAt() {
         return lastModifiedAt;
     }

     public void setLastModifiedAt(Date lastModifiedAt) {
         this.lastModifiedAt = lastModifiedAt;
     }

     @Nullable
     public User getCreatedBy() {
         return createdBy;
     }

     public void setCreatedBy(@Nullable User createdBy) {
         this.createdBy = createdBy;
     }
 }
