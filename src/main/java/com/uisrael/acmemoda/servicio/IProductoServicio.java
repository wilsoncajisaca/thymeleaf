package com.uisrael.acmemoda.servicio;

import java.util.List;

import com.uisrael.acmemoda.modelo.Producto;

public interface IProductoServicio {
	void saveProducto(Producto producto);
	void updateProducto(Producto producto);
	List<Producto> getProductos();
	Producto getProductoById(int id) throws Exception;
	void deleteProducto(int id);
	List<Producto> getProductosByIds(List<Integer> productoIds);
}
