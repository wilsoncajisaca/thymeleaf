package com.uisrael.acmemoda.controlador;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.uisrael.acmemoda.dto.PedidoDTO;
import com.uisrael.acmemoda.dto.ProductoDetalleDTO;
import com.uisrael.acmemoda.modelo.PedidoCabecera;
import com.uisrael.acmemoda.modelo.PedidoDetalle;
import com.uisrael.acmemoda.modelo.Producto;
import com.uisrael.acmemoda.servicio.IPedidoCabeceraServicio;
import com.uisrael.acmemoda.servicio.IPedidoDetalleService;
import com.uisrael.acmemoda.servicio.IProductoServicio;
import com.uisrael.acmemoda.servicio.IUsuarioServicio;

@Controller
@RequestMapping("/pedido")
public class PedidoControlador {
	@Autowired
	private IPedidoCabeceraServicio cabServicio;
	@Autowired
	private IPedidoDetalleService detServicio;
	@Autowired
	private IUsuarioServicio userService;
	@Autowired
	private IProductoServicio prodService;
	ObjectMapper objectMapper = new ObjectMapper();
	
	@GetMapping("/listar-pedido")
	public String getAll(Model model) {
		List<PedidoCabecera> obj = this.cabServicio.getPedidoCabecera();
		List<PedidoDetalle> objDet = this.detServicio.getPedidoDetalle();
		model.addAttribute("pedidosCabecera", obj);
		model.addAttribute("pedidosDetalle", objDet);
		return "/acmemoda/pages/pedidos";
	}
	
	@GetMapping("nuevo-pedido")
	public String crear(Model model) {		
		model.addAttribute("nuevoPedido", new PedidoDTO());
		model.addAttribute("clientes", userService.getUsuario());
		model.addAttribute("productos", prodService.getProductos());
		return "/acmemoda/pages/crear-pedido";
	}
 
	@PostMapping("guardar-pedido")
	private String guardar(@ModelAttribute("nuevoPedido") PedidoDTO obj) {
		try {
			PedidoCabecera newPedidoCab = new PedidoCabecera();
			List<ProductoDetalleDTO> detalles = objectMapper.readValue(obj.getDetallesPedidoJson(), new TypeReference<List<ProductoDetalleDTO>>() {});
			List<Producto> productos = prodService.getProductosByIds(detalles.stream().map(ProductoDetalleDTO::getProductoId).toList());
			Double totalPrecio = productos.stream()
                    .mapToDouble(Producto::getPrecio)
                    .sum();
			newPedidoCab.setFechaCompra(new Date());
			newPedidoCab.setNumeroFactura(obj.getNumeroFactura());
			newPedidoCab.setTotal(totalPrecio);
			newPedidoCab.setUsuario(obj.getUsuario());
			PedidoCabecera pedCabeceraEnt = cabServicio.savePedidoCabecera(newPedidoCab);
			for (ProductoDetalleDTO pedido : detalles) {
				PedidoDetalle detPedido = new PedidoDetalle();
				detPedido.setPedidoCabecera(pedCabeceraEnt);
				Producto producto = productos.stream()
                        .filter(p -> p.getProductoId() == (pedido.getProductoId()))
                        .findFirst()
                        .orElse(null);
				detPedido.setProducto(producto);
				detPedido.setCantidad(pedido.getCantidad());
				detPedido.setPrecioUnitario(producto.getPrecio());
				detPedido.setSubTotal(producto.getPrecio() * pedido.getCantidad());
				detServicio.savePedidoDetalle(detPedido);
			}
		} catch (Exception e) {
			System.out.println("No se logro guardar el pedido");
			e.printStackTrace();
		}
		return "redirect:/pedido/listar-pedido";
	}
	
	@GetMapping("eliminar-pedido/{id}")
	public String eliminar(@PathVariable("id") int id) throws Exception {
		detServicio.deleteByPedidoCabeceraId(id);
		cabServicio.deletePedidoCabecera(id);
		return "redirect:/pedido/listar-pedido";
	}

}
