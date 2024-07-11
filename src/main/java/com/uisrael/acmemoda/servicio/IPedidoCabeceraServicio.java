package com.uisrael.acmemoda.servicio;

import java.util.List;

import com.uisrael.acmemoda.modelo.PedidoCabecera;

public interface IPedidoCabeceraServicio {

	PedidoCabecera savePedidoCabecera(PedidoCabecera pedido);
	void updatePedidoCabecera(PedidoCabecera pedido);
	List<PedidoCabecera> getPedidoCabecera();
	PedidoCabecera getPedidoCabeceraById(int id) throws Exception;
	void deletePedidoCabecera(int id);
}
