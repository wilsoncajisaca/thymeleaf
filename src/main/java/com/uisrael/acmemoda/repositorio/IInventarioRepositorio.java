package com.uisrael.acmemoda.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uisrael.acmemoda.modelo.Inventario;

public interface IInventarioRepositorio extends JpaRepository<Inventario, Integer>{

}
