package com.uisrael.acmemoda.servicio.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uisrael.acmemoda.modelo.Categoria;
import com.uisrael.acmemoda.repositorio.ICategoriaRepositorio;
import com.uisrael.acmemoda.servicio.ICategoriaServicio;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CategoriaServicioImpl implements ICategoriaServicio{
	@Autowired
	private ICategoriaRepositorio categoriaRepositorio;
	@Override
	public void saveCategoria(Categoria categoria) {
		try {
			categoriaRepositorio.save(categoria);
			System.out.println("*** Registro Guardado ***");
		} catch (Exception e) {
			log.error("Error: {}", e);
		}
	}

	@Override
	public void updateCategoria(Categoria categoria) {
		try {
			if(categoria == null || categoria.getCategoriaId() == -1) {
				log.error("No se pudo encontrar el registro a actualizar");
				return;
			}
			Categoria categoriaAct = this.getCategoriaById(categoria.getCategoriaId());
			if (categoria.getNombre() != null && !categoria.getNombre().isEmpty()) {
			    categoriaAct.setNombre(categoria.getNombre());
			}
			if (categoria.getDescripcion() != null && !categoria.getDescripcion().isEmpty()) {
			    categoriaAct.setNombre(categoria.getDescripcion());
			}
			categoriaRepositorio.save(categoriaAct);
			System.out.println("*** Registro actualizado ***");
		} catch (Exception e) {
			log.error("Error: {}", e.getMessage());
		}
	}
	
	@Override
	public List<Categoria> getCategorias() {
		List<Categoria> categorias = new ArrayList<>();
		try {
			categorias = categoriaRepositorio.findAll();
		} catch (Exception e) {
			log.error("Error: {}", e);
		}
		return categorias;
	}

	@Override
	public Categoria getCategoriaById(Integer categoriaId) throws Exception {
		Optional<Categoria> categoriaOpt = categoriaRepositorio.findById(categoriaId);
		if(categoriaOpt.isPresent()){
			return categoriaOpt.get();
		}else {
			throw new Exception("No se encontro ningun registro");
		}
	}

	@Override
	public void deleteCategoria(Integer categoriaId) {
		try {
			categoriaRepositorio.deleteById(categoriaId);
			System.out.println("*** Registro Borrado ***");
		} catch (Exception e) {
			log.error("Error: {}", e);
		}
	}

}
