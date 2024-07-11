package com.uisrael.acmemoda.servicio;

import java.util.List;

import com.uisrael.acmemoda.modelo.Inventario;

public interface IInventarioServicio {
	void saveInventario(Inventario inventario);
	void updateInventario(Inventario inventario);
	List<Inventario> getInventarios();
	Inventario getInventarioById(int id) throws Exception;
	void deleteInventario(int id);
}
