package com.uisrael.acmemoda.servicio.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uisrael.acmemoda.modelo.Rol;
import com.uisrael.acmemoda.repositorio.IRolRepositorio;
import com.uisrael.acmemoda.servicio.IRolServicio;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class RolServicioImpl implements IRolServicio {
	@Autowired
	private IRolRepositorio repositorio;
	@Override
	public void saveRol(Rol obj) {
		try {
			repositorio.save(obj);
			System.out.println("*** Registro Guardado ***");
		} catch (Exception e) {
			log.error("Error: {}", e);
		}
	}

	@Override
	public void updateRol(Rol obj) {
		try {
			if(obj == null || obj.getRolId() == -1) {
				log.error("No se pudo encontrar el registro a actualizar");
				return;
			}
			Rol objAct = this.getRolById(obj.getRolId());
			if (obj.getNombre() != null) {
				objAct.setNombre(obj.getNombre());
			}
			repositorio.save(objAct);
			System.out.println("*** Registro actualizado ***");
		} catch (Exception e) {
			log.error("Error: {}", e.getMessage());
		}
	}

	@Override
	public List<Rol> getRol() {
		List<Rol> obj = new ArrayList<>();
		try {
			obj = repositorio.findAll();
		} catch (Exception e) {
			log.error("Error: {}", e);
		}
		return obj;
	}

	@Override
	public Rol getRolById(int id) throws Exception {
		Optional<Rol> objOpt = repositorio.findById(id);
		if(objOpt.isPresent()){
			return objOpt.get();
		}else {
			throw new Exception("No se encontro ningun registro");
		}
	}

	@Override
	public void deleteRol(int id) {
		try {
			repositorio.deleteById(id);
			System.out.println("*** Registro Borrado ***");
		} catch (Exception e) {
			log.error("Error: {}", e);
		}
	}
}
