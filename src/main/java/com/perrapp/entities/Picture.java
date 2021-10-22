package com.perrapp.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@RequiredArgsConstructor
public final class Picture extends Audit {

	private static final long serialVersionUID = 4071780217025039863L;

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;

	@NonNull
	@Column(name = "folder", length = 255)
	private String folder;

	@NonNull
	@Column(name = "uri", length = 255)
	private String uri;

	@NonNull
	@Column(name = "name", length = 255)
	private String name;

}
