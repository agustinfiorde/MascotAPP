package com.perrapp.entities;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class Audit implements Serializable {

	protected static final long serialVersionUID = 1L;

	@CreatedDate
	@Column(name = "CREATED_DATE", nullable = false, updatable = false)
	protected LocalDateTime createdDate;

	@LastModifiedDate
	@Column(name = "UPDATED_DATE", nullable = false)
	protected LocalDateTime updatedDate;
	
	@Column(name = "active", columnDefinition = "Bit(1) default true")
	protected boolean active = true;

	@PrePersist
	public void upload() {
		this.active = true;
	}
	
	public void cancel() {
		this.active = false;
	}
	
}
