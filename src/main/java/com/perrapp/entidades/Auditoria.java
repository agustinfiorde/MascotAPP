package com.perrapp.entidades;

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
public abstract class Auditoria implements Serializable {

	protected static final long serialVersionUID = 1L;

	@CreatedDate
	@Column(name = "FEC_CREACION", nullable = false, updatable = false)
	protected LocalDateTime createdDate;

	@LastModifiedDate
	@Column(name = "FEC_MODIFICACION", nullable = false)
	protected LocalDateTime fechaModificacion;
	
	@Column(name = "activo", columnDefinition = "Bit(1) default true")
	protected boolean activo = true;

	@PrePersist
	public void alta() {
		this.activo = true;
	}
	
	public void baja() {
		this.activo = false;
	}
	
}
