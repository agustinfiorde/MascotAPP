package com.perrapp.entidades;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
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
public final class Mascota extends Auditoria {

	private static final long serialVersionUID = 4071780217025039863L;

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;

	@Column(name = "apodo", length = 32)
	private String apodo;
	@Column(name = "numero_identificatorio", length = 32)
	private String numeroIdentificatorio;
	@Column(name = "raza", length = 32)
	private String raza;

	@Column(name = "nacimiento")
	private LocalDate nacimiento;

	@ManyToOne
	private Usuario propietario;

	@OneToOne
	private Imagen fotoPerfil;

	@OneToMany
	private List<Imagen> fotos;


}
