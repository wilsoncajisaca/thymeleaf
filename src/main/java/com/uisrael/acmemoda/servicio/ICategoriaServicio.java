package com.uisrael.acmemoda.servicio;

import java.util.List;

import com.uisrael.acmemoda.modelo.Categoria;

public interface ICategoriaServicio {
	void saveCategoria(Categoria categoria);
	void updateCategoria(Categoria categoria);
	List<Categoria> getCategorias();
	Categoria getCategoriaById(Integer categoriaId) throws Exception;
	void deleteCategoria(Integer categoriaId);
}