package com.uisrael.acmemoda.servicio;

import java.util.List;

import com.uisrael.acmemoda.modelo.PedidoDetalle;

public interface IPedidoDetalleService {
	void savePedidoDetalle(PedidoDetalle pedidoDet);
	void updatePedidoDetalle(PedidoDetalle pedidoDet);
	List<PedidoDetalle> getPedidoDetalle();
	PedidoDetalle getPedidoDetalleById(int id) throws Exception;
	void deletePedidoDetalle(int id);
	List<PedidoDetalle> getPedidoDetalleByCabeceraId(Integer cabeceraId);
	void deleteByPedidoCabeceraId(Integer cabeceraId);
}
