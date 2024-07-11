package com.uisrael.acmemoda.servicio.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uisrael.acmemoda.modelo.Inventario;
import com.uisrael.acmemoda.repositorio.IInventarioRepositorio;
import com.uisrael.acmemoda.servicio.IInventarioServicio;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class InventarioServicioImpl implements IInventarioServicio {
	@Autowired
	private IInventarioRepositorio inventarioRepositorio;
	@Override
	public void saveInventario(Inventario inventario) {
		try {
			inventarioRepositorio.save(inventario);
			System.out.println("*** Registro Guardado ***");
		} catch (Exception e) {
			log.error("Error: {}", e);
		}
	}

	@Override
	public void updateInventario(Inventario inventario) {
		try {
			if(inventario == null || inventario.getInventarioId() == -1) {
				log.error("No se pudo encontrar el registro a actualizar");
				return;
			}
			Inventario invAct = this.getInventarioById(inventario.getInventarioId());
			if (inventario.getProducto() != null) {
				invAct.setProducto(inventario.getProducto());
			}
			if (inventario.getFechaActualizacion() != null) {
				invAct.setFechaActualizacion(inventario.getFechaActualizacion());
			}
			invAct.setStock(inventario.getStock());
			inventarioRepositorio.save(invAct);
			System.out.println("*** Registro actualizado ***");
		} catch (Exception e) {
			log.error("Error: {}", e.getMessage());
		}
	}

	@Override
	public List<Inventario> getInventarios() {
		List<Inventario> inventarios = new ArrayList<>();
		try {
			inventarios = inventarioRepositorio.findAll();
		} catch (Exception e) {
			log.error("Error: {}", e);
		}
		return inventarios;
	}

	@Override
	public Inventario getInventarioById(int id) throws Exception {
		Optional<Inventario> objOpt = inventarioRepositorio.findById(id);
		if(objOpt.isPresent()){
			return objOpt.get();
		}else {
			throw new Exception("No se encontro ningun registro");
		}
	}

	@Override
	public void deleteInventario(int id) {
		try {
			inventarioRepositorio.deleteById(id);
			System.out.println("*** Registro Borrado ***");
		} catch (Exception e) {
			log.error("Error: {}", e);
		}
	}

}
