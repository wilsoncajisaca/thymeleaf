package com.uisrael.acmemoda.dto;

import java.util.Random;

import com.uisrael.acmemoda.modelo.Usuario;

import lombok.Data;

@Data
public class PedidoDTO {
	private int pedidoId;
	private String numeroFactura;
	private Usuario usuario;
	private String detallesPedidoJson;
	
	public String getNumeroFactura() {
		Random random = new Random();
        int min = 100000000;
        int max = 999999999;
        int randomNumber = random.nextInt((max - min) + 1) + min;
		return String.valueOf(randomNumber);
	}

	public void setNumeroFactura(String numeroFactura) {
		this.numeroFactura = numeroFactura;
	}
}
