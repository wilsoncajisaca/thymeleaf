package com.uisrael.acmemoda.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
public class Producto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int productoId = -1;
	private String nombre;
	private String descripcion;
	private int talla;
	private String color;
	private String imagenUrl;
	private Double precio;
	
	@ManyToOne
	@JoinColumn(columnDefinition = "categoria_id")
	private Categoria categoria;
	
	@OneToMany(mappedBy = "producto")
	@ToString.Exclude
	private List<Inventario> inventarios = new ArrayList<>();
	
	@OneToMany(mappedBy = "producto")
	@ToString.Exclude
	private List<PedidoDetalle> detalles = new ArrayList<>();
}
