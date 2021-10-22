package com.perrapp.entities.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public abstract class AuditDTO implements Serializable {

	protected static final long serialVersionUID = 1L;

	protected boolean active = true;

	protected LocalDateTime createdDate;

	protected LocalDateTime updatedDate;

}
