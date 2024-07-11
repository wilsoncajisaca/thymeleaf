package com.uisrael.acmemoda.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.uisrael.acmemoda.modelo.Rol;
import com.uisrael.acmemoda.servicio.IRolServicio;

@Controller
@RequestMapping("/rol")
public class RoleControlador {
	@Autowired
	private IRolServicio rolServicio;

	@GetMapping("/index")
	public String index() {
		return "redirect:/rol/listar-rol";
	}

	@GetMapping("/listar-rol")
	public String getAll(Model model) {
		List<Rol> obj = this.rolServicio.getRol();
		model.addAttribute("roles", obj);
		return "/acmemoda/pages/rol";
	}

	@GetMapping("nuevo-rol")
	public String crear(Model model) {
		model.addAttribute("nuevoRol", new Rol());
		return "/acmemoda/pages/crear-rol";
	}

	@PostMapping("guardar-rol")
	private String guardar(@ModelAttribute("rolId") Rol obj) {
		rolServicio.saveRol(obj);
		return "redirect:/rol/listar-rol";
	}

	@GetMapping("editar-rol/{rolId}")
	public String editar(@PathVariable("rolId") int id, Model model) throws Exception {
		Rol obj = rolServicio.getRolById(id);
		model.addAttribute("nuevoRol", obj);
		return "/acmemoda/pages/crear-rol";
	}
	
	@GetMapping("eliminar-rol/{rolId}")
	public String eliminar(@PathVariable("rolId") int id) throws Exception {
		rolServicio.deleteRol(id);
		return "redirect:/rol/listar-rol";
	}
}
