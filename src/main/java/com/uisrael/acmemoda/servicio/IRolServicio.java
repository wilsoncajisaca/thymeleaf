package com.uisrael.acmemoda.servicio;

import java.util.List;

import com.uisrael.acmemoda.modelo.Rol;

public interface IRolServicio {

	void saveRol(Rol rol);
	void updateRol(Rol rol);
	List<Rol> getRol();
	Rol getRolById(int id) throws Exception;
	void deleteRol(int id);
}
