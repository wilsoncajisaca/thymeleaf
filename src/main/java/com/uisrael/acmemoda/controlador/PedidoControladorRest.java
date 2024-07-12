package com.uisrael.acmemoda.controlador;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.uisrael.acmemoda.dto.ProductoDetalleDTO;
import com.uisrael.acmemoda.modelo.PedidoDetalle;
import com.uisrael.acmemoda.servicio.IPedidoDetalleService;

@RestController
public class PedidoControladorRest {
	@Autowired
	private IPedidoDetalleService detServicio;
	@GetMapping("pedido/mostrar-detalle/{cabecera_id}")
	public List<ProductoDetalleDTO> detallePedido(@PathVariable(value = "cabecera_id") final Integer cabeceraId){
		List<PedidoDetalle> objDet = this.detServicio.getPedidoDetalleByCabeceraId(cabeceraId);
		List<ProductoDetalleDTO> detalles = new ArrayList<>();
		for (PedidoDetalle det : objDet) {
			ProductoDetalleDTO dto = new ProductoDetalleDTO();
			dto.setNombreProducto(det.getProducto().getNombre());
			dto.setCantidad(det.getCantidad());
			dto.setPrecioUnitario(det.getPrecioUnitario());
			dto.setSubTotal(det.getSubTotal());
			detalles.add(dto);
		}
	    return detalles;
	}

}
