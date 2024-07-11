package com.uisrael.acmemoda.dto;

import lombok.Data;

@Data
public class ProductoDetalleDTO {
	private Integer productoId;
	private Integer cantidad;
	private String nombreProducto;
	private double precioUnitario;
	private double subTotal;
}
