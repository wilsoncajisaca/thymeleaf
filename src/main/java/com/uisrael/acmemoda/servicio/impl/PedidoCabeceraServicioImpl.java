package com.uisrael.acmemoda.servicio.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uisrael.acmemoda.modelo.PedidoCabecera;
import com.uisrael.acmemoda.repositorio.IPedidoCabeceraRepositorio;
import com.uisrael.acmemoda.servicio.IPedidoCabeceraServicio;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PedidoCabeceraServicioImpl implements IPedidoCabeceraServicio{
	@Autowired
	private IPedidoCabeceraRepositorio repositorio;
	
	@Override
	public PedidoCabecera savePedidoCabecera(PedidoCabecera obj) {
		PedidoCabecera pedCabeceraEnt = new PedidoCabecera();
		try {
			pedCabeceraEnt = repositorio.save(obj);
			System.out.println("*** Registro Guardado ***");
		} catch (Exception e) {
			log.error("Error: {}", e);
		}
		return pedCabeceraEnt;
	}

	@Override
	public void updatePedidoCabecera(PedidoCabecera obj) {
		try {
			if(obj == null || obj.getPedidoCabeceraId() == -1) {
				log.error("No se pudo encontrar el registro a actualizar");
				return;
			}
			PedidoCabecera objAct = this.getPedidoCabeceraById(obj.getPedidoCabeceraId());
			if (obj.getUsuario() != null) {
				objAct.setUsuario(obj.getUsuario());
			}
			objAct.setTotal(obj.getTotal());
			repositorio.save(objAct);
			System.out.println("*** Registro actualizado ***");
		} catch (Exception e) {
			log.error("Error: {}", e.getMessage());
		}
	}

	@Override
	public List<PedidoCabecera> getPedidoCabecera() {
		List<PedidoCabecera> obj = new ArrayList<>();
		try {
			obj = repositorio.findAll();
		} catch (Exception e) {
			log.error("Error: {}", e);
		}
		return obj;
	}

	@Override
	public PedidoCabecera getPedidoCabeceraById(int id) throws Exception {
		Optional<PedidoCabecera> objOpt = repositorio.findById(id);
		if(objOpt.isPresent()){
			return objOpt.get();
		}else {
			throw new Exception("No se encontro ningun registro");
		}
	}

	@Override
	public void deletePedidoCabecera(int id) {
		try {
			repositorio.deleteById(id);
			System.out.println("*** Registro Borrado ***");
		} catch (Exception e) {
			log.error("Error: {}", e);
		}
	}

}
