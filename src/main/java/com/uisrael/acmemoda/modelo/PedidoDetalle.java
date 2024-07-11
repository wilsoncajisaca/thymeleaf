package com.uisrael.acmemoda.modelo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table
public class PedidoDetalle implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int pedidoDetalleId = -1;
	
	private int cantidad;
	private double precioUnitario;
	private double subTotal;
	
	@ManyToOne
	@JoinColumn(columnDefinition = "pedido_cabecera_id")
	@JsonIgnore
	private PedidoCabecera pedidoCabecera;
	
	@ManyToOne
	@JoinColumn(columnDefinition = "producto_id")
	@JsonIgnore
	private Producto producto;
}
