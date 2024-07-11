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
import com.uisrael.acmemoda.servicio.ICategoriaServicio;

@Controller
@RequestMapping("/categoria")
public class CategoriaControlador {
	
	@Autowired
	private ICategoriaServicio categoriaServicio;
	
	@GetMapping("/index")
	public String index() {
		return "redirect:/categoria/listar-categoria";
	}
	
	@GetMapping("/listar-categoria")
	public String categoria(Model model) {
		List<Categoria> listaCursos = this.categoriaServicio.getCategorias();
		model.addAttribute("categorias", listaCursos);
		return "/acmemoda/pages/categoria";
	}
	
	@GetMapping("nueva-categoria")
	public String curso(Model model) {
		model.addAttribute("nuevaCategoria", new Categoria());
		return "/acmemoda/pages/crear-categoria";
	}
	
	@PostMapping("guardar-categoria")
	private String guardarCategoria(@ModelAttribute("nuevaCategoria")Categoria categoria) {
		categoriaServicio.saveCategoria(categoria);
		return "redirect:/categoria/listar-categoria";
	}
	
	@GetMapping("eliminar-categoria/{idCategoria}")
	public String editarCurso(@PathVariable("idCategoria") int categoriaId) throws Exception {
		categoriaServicio.deleteCategoria(categoriaId);
		return "redirect:/categoria/listar-categoria";
	}
	
	@GetMapping("editar-categoria/{idCategoria}")
	public String editarCurso(@PathVariable("idCategoria") int categoriaId, Model model) throws Exception {
		Categoria updCategoria = categoriaServicio.getCategoriaById(categoriaId);
		model.addAttribute("nuevaCategoria", updCategoria);
		return "/acmemoda/pages/crear-categoria";
	}
}