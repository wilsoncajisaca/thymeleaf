package com.uisrael.acmemoda.servicio.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uisrael.acmemoda.modelo.PedidoDetalle;
import com.uisrael.acmemoda.repositorio.IPedidoDetalleRepositorio;
import com.uisrael.acmemoda.servicio.IPedidoDetalleService;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PedidoDetalleServiceImpl implements IPedidoDetalleService{
	@Autowired
	private IPedidoDetalleRepositorio repositorio;
	@Override
	public void savePedidoDetalle(PedidoDetalle obj) {
		try {
			repositorio.save(obj);
			System.out.println("*** Registro Guardado ***");
		} catch (Exception e) {
			log.error("Error: {}", e);
		}
	}

	@Override
	public void updatePedidoDetalle(PedidoDetalle obj) {
		try {
			if(obj == null || obj.getPedidoDetalleId() == -1) {
				log.error("No se pudo encontrar el registro a actualizar");
				return;
			}
			PedidoDetalle objAct = this.getPedidoDetalleById(obj.getPedidoDetalleId());
			if (obj.getPedidoCabecera() != null) {
				objAct.setPedidoCabecera(obj.getPedidoCabecera());
			}
			objAct.setSubTotal(obj.getSubTotal());
			repositorio.save(objAct);
			System.out.println("*** Registro actualizado ***");
		} catch (Exception e) {
			log.error("Error: {}", e.getMessage());
		}
	}

	@Override
	public List<PedidoDetalle> getPedidoDetalle() {
		List<PedidoDetalle> obj = new ArrayList<>();
		try {
			obj = repositorio.findAll();
		} catch (Exception e) {
			log.error("Error: {}", e);
		}
		return obj;
	}

	@Override
	public PedidoDetalle getPedidoDetalleById(int id) throws Exception {
		Optional<PedidoDetalle> objOpt = repositorio.findById(id);
		if(objOpt.isPresent()){
			return objOpt.get();
		}else {
			throw new Exception("No se encontro ningun registro");
		}
	}

	@Override
	public void deletePedidoDetalle(int id) {
		try {
			repositorio.deleteById(id);
			System.out.println("*** Registro Borrado ***");
		} catch (Exception e) {
			log.error("Error: {}", e);
		}
	}

	@Override
	public List<PedidoDetalle> getPedidoDetalleByCabeceraId(Integer cabeceraId) {
		List<PedidoDetalle> obj = new ArrayList<>();
		try {
			obj = repositorio.findByPedidoCabeceraId(cabeceraId);
		} catch (Exception e) {
			log.error("Error: {}", e);
		}
		return obj;
	}

	@Override
	@Transactional
	public void deleteByPedidoCabeceraId(Integer cabeceraId) {
		try {
			repositorio.deleteByPedidoCabeceraId(cabeceraId);
			System.out.println("*** Registros Borrados ***");
		} catch (Exception e) {
			log.error("Error: {}", e);
		}
	}

}
