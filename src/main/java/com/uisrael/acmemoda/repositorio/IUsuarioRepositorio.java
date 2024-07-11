package com.uisrael.acmemoda.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uisrael.acmemoda.modelo.Usuario;

public interface IUsuarioRepositorio extends JpaRepository<Usuario, Integer> {

}
