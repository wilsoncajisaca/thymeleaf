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

import com.uisrael.acmemoda.modelo.Inventario;
import com.uisrael.acmemoda.modelo.Producto;
import com.uisrael.acmemoda.servicio.IInventarioServicio;
import com.uisrael.acmemoda.servicio.IProductoServicio;

@Controller
@RequestMapping("/inventario")
public class InventarioController {
	@Autowired
	IInventarioServicio servicio;
	@Autowired
	IProductoServicio prodServicio;
	
	@GetMapping("/index")
	public String index() {
		return "redirect:/inventario/listar-inventario";
	}

	@GetMapping("/listar-inventario")
	public String getAll(Model model) {
		List<Inventario> obj = this.servicio.getInventarios();
		model.addAttribute("inventarios", obj);
		return "/acmemoda/pages/inventario";
	}

	@GetMapping("nuevo-inventario")
	public String crear(Model model) {
		List<Producto> productos = prodServicio.getProductos();
        model.addAttribute("productos", productos);
		model.addAttribute("nuevoInventario", new Inventario());
		return "/acmemoda/pages/crear-inventario";
	}

	@PostMapping("guardar-inventario")
	private String guardar(@ModelAttribute("id") Inventario obj) {
		servicio.saveInventario(obj);
		return "redirect:/inventario/listar-inventario";
	}

	@GetMapping("editar-inventario/{id}")
	public String editar(@PathVariable("id") int id, Model model) throws Exception {
		Inventario obj = servicio.getInventarioById(id);
		List<Producto> productos = prodServicio.getProductos();
        model.addAttribute("productos", productos);
		model.addAttribute("nuevoInventario", obj);
		return "/acmemoda/pages/crear-inventario";
	}
	
	@GetMapping("eliminar-inventario/{id}")
	public String eliminar(@PathVariable("id") int id) throws Exception {
		servicio.deleteInventario(id);
		return "redirect:/inventario/listar-inventario";
	}
}
