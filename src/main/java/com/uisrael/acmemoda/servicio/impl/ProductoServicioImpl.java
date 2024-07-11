package com.uisrael.acmemoda.servicio.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uisrael.acmemoda.modelo.Producto;
import com.uisrael.acmemoda.repositorio.IProductoRepositorio;
import com.uisrael.acmemoda.servicio.IProductoServicio;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProductoServicioImpl implements IProductoServicio {
	@Autowired
	private IProductoRepositorio productoRepositorio;
	@Override
	public void saveProducto(Producto producto) {
		try {
			productoRepositorio.save(producto);
			System.out.println("*** Registro Guardado ***");
		} catch (Exception e) {
			log.error("Error: {}", e);
		}
	}

	@Override
	public void updateProducto(Producto producto) {
		try {
			if(producto == null || producto.getProductoId() == -1) {
				log.error("No se pudo encontrar el registro a actualizar");
				return;
			}
			Producto prodAct = this.getProductoById(producto.getProductoId());
			if (producto.getNombre() != null && !producto.getNombre().isEmpty()) {
				prodAct.setNombre(producto.getNombre());
			}
			if (producto.getDescripcion() != null && !producto.getDescripcion().isEmpty()) {
				prodAct.setNombre(producto.getDescripcion());
			}
			
			productoRepositorio.save(prodAct);
			System.out.println("*** Registro actualizado ***");
		} catch (Exception e) {
			log.error("Error: {}", e.getMessage());
		}
	}

	@Override
	public List<Producto> getProductos() {
		List<Producto> productos = new ArrayList<>();
		try {
			productos = productoRepositorio.findAll();
		} catch (Exception e) {
			log.error("Error: {}", e);
		}
		return productos;
	}

	@Override
	public Producto getProductoById(int id) throws Exception {
		Optional<Producto> prodOpt = productoRepositorio.findById(id);
		if(prodOpt.isPresent()){
			return prodOpt.get();
		}else {
			throw new Exception("No se encontro ningun registro");
		}
	}

	@Override
	public void deleteProducto(int id) {
		try {
			productoRepositorio.deleteById(id);
			System.out.println("*** Registro Borrado ***");
		} catch (Exception e) {
			log.error("Error: {}", e);
		}
	}

	@Override
	public List<Producto> getProductosByIds(List<Integer> productoIds) {
		List<Producto> productos = new ArrayList<>();
		try {
			productos = productoRepositorio.findByProductoIdIn(productoIds);
		} catch (Exception e) {
			log.error("Error: {}", e);
		}
		return productos;
	}

}
