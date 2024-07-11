package com.uisrael.acmemoda.servicio;

import java.util.List;

import com.uisrael.acmemoda.modelo.Usuario;

public interface IUsuarioServicio {
	void saveUsuario(Usuario usuario);
	void updateUsuario(Usuario usuario);
	List<Usuario> getUsuario();
	Usuario getUsuarioById(int id) throws Exception;
	void deleteUsuario(int id);
}
