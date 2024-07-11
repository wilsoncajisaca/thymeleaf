package com.uisrael.acmemoda.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uisrael.acmemoda.modelo.Producto;

public interface IProductoRepositorio extends JpaRepository<Producto, Integer> {
	List<Producto> findByProductoIdIn(List<Integer> productIds);
}
