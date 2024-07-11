package com.uisrael.acmemoda.servicio.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uisrael.acmemoda.modelo.Usuario;
import com.uisrael.acmemoda.repositorio.IUsuarioRepositorio;
import com.uisrael.acmemoda.servicio.IUsuarioServicio;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UsuarioServicioImpl implements IUsuarioServicio {
	@Autowired
	private IUsuarioRepositorio repositorio;

	@Override
	public void saveUsuario(Usuario obj) {
		try {
			repositorio.save(obj);
			System.out.println("*** Registro Guardado ***");
		} catch (Exception e) {
			log.error("Error: {}", e);
		}
	}

	@Override
	public void updateUsuario(Usuario obj) {
		try {
			if(obj == null || obj.getUsuarioId() == -1) {
				log.error("No se pudo encontrar el registro a actualizar");
				return;
			}
			Usuario objAct = this.getUsuarioById(obj.getUsuarioId());
			if (obj.getCorreo() != null) {
				objAct.setCorreo(obj.getCorreo());
			}
			repositorio.save(objAct);
			System.out.println("*** Registro actualizado ***");
		} catch (Exception e) {
			log.error("Error: {}", e.getMessage());
		}
	}

	@Override
	public List<Usuario> getUsuario() {
		List<Usuario> obj = new ArrayList<>();
		try {
			obj = repositorio.findAll();
		} catch (Exception e) {
			log.error("Error: {}", e);
		}
		return obj;
	}

	@Override
	public Usuario getUsuarioById(int id) throws Exception {
		Optional<Usuario> objOpt = repositorio.findById(id);
		if(objOpt.isPresent()){
			return objOpt.get();
		}else {
			throw new Exception("No se encontro ningun registro");
		}
	}

	@Override
	public void deleteUsuario(int id) {
		try {
			repositorio.deleteById(id);
			System.out.println("*** Registro Borrado ***");
		} catch (Exception e) {
			log.error("Error: {}", e);
		}
	}

}
