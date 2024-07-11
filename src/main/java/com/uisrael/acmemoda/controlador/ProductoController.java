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

import com.uisrael.acmemoda.modelo.Categoria;
import com.uisrael.acmemoda.modelo.Producto;
import com.uisrael.acmemoda.servicio.ICategoriaServicio;
import com.uisrael.acmemoda.servicio.IProductoServicio;

@Controller
@RequestMapping("/producto")
public class ProductoController {
	@Autowired
	private IProductoServicio servicio;
	@Autowired
	private ICategoriaServicio categoriaServicio;
	
	@GetMapping("/index")
	public String index() {
		return "redirect:/producto/listar-producto";
	}

	@GetMapping("/listar-producto")
	public String getAll(Model model) {
		List<Producto> obj = this.servicio.getProductos();
		model.addAttribute("productos", obj);
		return "/acmemoda/pages/producto";
	}

	@GetMapping("nuevo-producto")
	public String crear(Model model) {
		List<Categoria> categorias = categoriaServicio.getCategorias();
        model.addAttribute("categorias", categorias);
		model.addAttribute("nuevoProducto", new Producto());
		return "/acmemoda/pages/crear-producto";
	}

	@PostMapping("guardar-producto")
	private String guardar(@ModelAttribute("nuevoProducto") Producto obj) {
		servicio.saveProducto(obj);
		return "redirect:/producto/listar-producto";
	}

	@GetMapping("editar-producto/{id}")
	public String editar(@PathVariable("id") int id, Model model) throws Exception {
		Producto obj = servicio.getProductoById(id);
		List<Categoria> categorias = categoriaServicio.getCategorias();
        model.addAttribute("categorias", categorias);
		model.addAttribute("nuevoProducto", obj);
		return "/acmemoda/pages/crear-producto";
	}
	
	@GetMapping("eliminar-producto/{id}")
	public String eliminar(@PathVariable("id") int id) throws Exception {
		servicio.deleteProducto(id);
		return "redirect:/producto/listar-producto";
	}
}
