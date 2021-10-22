package com.perrapp.entities;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@Builder
@AllArgsConstructor
public final class Pet extends Audit {

	private static final long serialVersionUID = 4071780217025039863L;

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;

	@Column(name = "nickname", length = 32)
	private String nickname;
	@Column(name = "petNumber", length = 32)
	private String petNumber;
	@Column(name = "breed", length = 32)
	private String breed;

	@Column(name = "born")
	private LocalDate born;

	@OneToOne
	private Picture profilePicture;

	@OneToMany
	private List<Picture> photos;


}
