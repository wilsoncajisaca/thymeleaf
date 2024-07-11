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
import com.uisrael.acmemoda.modelo.Usuario;
import com.uisrael.acmemoda.servicio.IRolServicio;
import com.uisrael.acmemoda.servicio.IUsuarioServicio;

@Controller
@RequestMapping("/usuario")
public class UsuarioControlador {
	@Autowired
	private IUsuarioServicio servicio;
	@Autowired
	private IRolServicio rolServicio;

	@GetMapping("/index")
	public String index() {
		return "redirect:/usuario/listar-usuario";
	}

	@GetMapping("/listar-usuario")
	public String getAll(Model model) {
		List<Usuario> obj = this.servicio.getUsuario();
		model.addAttribute("usuarios", obj);
		return "/acmemoda/pages/usuario";
	}

	@GetMapping("nuevo-usuario")
	public String crear(Model model) {
		List<Rol> roles = rolServicio.getRol();
        model.addAttribute("roles", roles);
		model.addAttribute("nuevoUsuario", new Usuario());
		return "/acmemoda/pages/crear-usuario";
	}

	@PostMapping("guardar-usuario")
	private String guardar(@ModelAttribute("id") Usuario obj) {
		servicio.saveUsuario(obj);
		return "redirect:/usuario/listar-usuario";
	}

	@GetMapping("editar-usuario/{id}")
	public String editar(@PathVariable("id") int id, Model model) throws Exception {
		Usuario obj = servicio.getUsuarioById(id);
		List<Rol> roles = rolServicio.getRol();
        model.addAttribute("roles", roles);
		model.addAttribute("nuevoUsuario", obj);
		return "/acmemoda/pages/crear-usuario";
	}
	
	@GetMapping("eliminar-usuario/{id}")
	public String eliminar(@PathVariable("id") int id) throws Exception {
		servicio.deleteUsuario(id);
		return "redirect:/usuario/listar-usuario";
	}
}
