package com.perrapp.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
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
public final class User extends Audit {

	private static final long serialVersionUID = 4071780217025039863L;

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;

	@ManyToMany(fetch = FetchType.EAGER)
	private List<Role> roles;

	@Column(name = "name", length = 32)
	private String name;
	@Column(name = "lastName", length = 32)
	private String lastName;

	@Column(name = "email", length = 32)
	private String email;
	@Column(name = "password", length = 64)
	private String password;

	@Column(name = "dni", length = 8)
	private String dni;

	@OneToMany
	private List<Pet> pets;
	
	@OneToOne
	private Pet favoritePet;
	
	@OneToOne
	private Picture profilePicture;
	
}
