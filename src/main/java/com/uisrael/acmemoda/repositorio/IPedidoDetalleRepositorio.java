package com.uisrael.acmemoda.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.uisrael.acmemoda.modelo.PedidoDetalle;

public interface IPedidoDetalleRepositorio extends JpaRepository<PedidoDetalle, Integer> {
	@Query("SELECT pd FROM PedidoDetalle pd WHERE pd.pedidoCabecera.pedidoCabeceraId = ?1")
	List<PedidoDetalle> findByPedidoCabeceraId(Integer pedidoCabeceraId);
	
	@Modifying
	@Query("DELETE FROM PedidoDetalle pd WHERE pd.pedidoCabecera.pedidoCabeceraId = ?1")
	void deleteByPedidoCabeceraId(Integer pedidoCabeceraId);
}
