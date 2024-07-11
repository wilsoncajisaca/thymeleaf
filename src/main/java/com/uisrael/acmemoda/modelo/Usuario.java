package com.uisrael.acmemoda.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.ToString;

@Data
@Entity
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int usuarioId = -1;
	
	@Column(length = 150)
	private String nombre;
	
	@Column(length = 150, unique = true)
	private String correo;
	
	private String contrasenia;
	
	@ManyToOne
	@JoinColumn(columnDefinition = "rol_id", nullable = false)
	private Rol rol;
	
	@OneToMany(mappedBy = "usuario")
	@ToString.Exclude
	private List<PedidoCabecera> cabeceras = new ArrayList<>();
}
