package com.uisrael.acmemoda;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.uisrael.acmemoda.modelo.Categoria;
import com.uisrael.acmemoda.modelo.Inventario;
import com.uisrael.acmemoda.modelo.PedidoCabecera;
import com.uisrael.acmemoda.modelo.PedidoDetalle;
import com.uisrael.acmemoda.modelo.Producto;
import com.uisrael.acmemoda.modelo.Rol;
import com.uisrael.acmemoda.modelo.Usuario;
import com.uisrael.acmemoda.servicio.ICategoriaServicio;
import com.uisrael.acmemoda.servicio.IInventarioServicio;
import com.uisrael.acmemoda.servicio.IPedidoCabeceraServicio;
import com.uisrael.acmemoda.servicio.IPedidoDetalleService;
import com.uisrael.acmemoda.servicio.IProductoServicio;
import com.uisrael.acmemoda.servicio.IRolServicio;
import com.uisrael.acmemoda.servicio.IUsuarioServicio;

@SpringBootTest
class AcmemodaApplicationTests {
	@Autowired
	private ICategoriaServicio categoriaServicio;
	@Autowired
	private IProductoServicio productoServicio;
	@Autowired
	private IInventarioServicio inventarioServicio;
	@Autowired
	private IPedidoCabeceraServicio pedidoCabeceraServicio;
	@Autowired
	private IPedidoDetalleService pedidoDetalleService;
	@Autowired
	private IRolServicio rolServicio;
	@Autowired
	private IUsuarioServicio usuarioServicio;
	
	@Test
	void contextLoads() {
		this.categoriaCRUD();
		this.productoCRUD();
		this.inventarioCRUD();
		this.rolCRUD();
		this.usuarioCRUD();
		this.pedidoCabeceraCRUD();
		this.pedidoDetalleCRUD();
	}
	
	private void categoriaCRUD() {
		try {
			Categoria categoria1 = new Categoria();
			categoria1.setNombre("Deportiva");
			categoria1.setDescripcion("Ropa deportiva");
			this.categoriaServicio.saveCategoria(categoria1);
			
			Categoria categoria2 = new Categoria();
			categoria2.setNombre("Casual");
			categoria2.setDescripcion("Ropa");
			this.categoriaServicio.saveCategoria(categoria2);
			
			System.out.println("*** TODAS LAS CATEGORIAS ***");
			List<Categoria> categorias = this.categoriaServicio.getCategorias();
			for (Categoria categoria : categorias) {
				System.out.println(categoria);		
			}
			
			System.out.println("*** CATEGORIA ESPECIFICA ***");
			System.out.println("*** " + this.categoriaServicio.getCategoriaById(1) + " ***");
			
			Categoria categoriaAct = new Categoria();
			categoriaAct.setCategoriaId(2);
			categoriaAct.setDescripcion("Ropa Casual");
			this.categoriaServicio.updateCategoria(categoriaAct);
			System.out.println("*** " + this.categoriaServicio.getCategoriaById(2) + " ***");
			
			this.categoriaServicio.deleteCategoria(1);
		}catch(Exception e) {
			System.out.println("Error: " + e.getMessage() );
		}
	}

	private void productoCRUD() {
		try {
			Producto producto = new Producto();
			producto.setNombre("Wilson Cajisaca");
			producto.setCategoria(this.categoriaServicio.getCategoriaById(2));
			producto.setColor("Rojo");
			producto.setDescripcion("Chompa roja actual");
			producto.setImagenUrl("S/N");
			producto.setTalla(20);
			productoServicio.saveProducto(producto);
			
			List<Producto> productos = this.productoServicio.getProductos();
			for (Producto prod : productos) {
				System.out.println("***" + prod + "***");
			}
			
			System.out.println("*** PRODUCTO ESPECIFICO ***");
			System.out.println(this.productoServicio.getProductoById(1));
			
			producto.setColor("Azul");
			producto.setDescripcion("Chopa azul actual");
			producto.setProductoId(1);
			this.productoServicio.updateProducto(producto);
			System.out.println(this.productoServicio.getProductoById(1));
			
			//this.productoServicio.deleteProducto(1);
		}catch (Exception e) {
			System.out.println("Error: " + e.getMessage() );
		}
	}
	
	private void inventarioCRUD() {
		try {
			Inventario inventario = new Inventario();
			inventario.setFechaActualizacion(new Date());
			inventario.setProducto(this.productoServicio.getProductoById(1));
			inventario.setStock(1000);
			inventarioServicio.saveInventario(inventario);
			
			List<Inventario> inventarios = this.inventarioServicio.getInventarios();
			for (Inventario inv : inventarios) {
				System.out.println("***" + inv + "***");
			}
			
			System.out.println("*** PRODUCTO ESPECIFICO ***");
			System.out.println(this.inventarioServicio.getInventarioById(1));
			
			inventario.setStock(2000);
			inventario.setInventarioId(1);
			this.inventarioServicio.updateInventario(inventario);
			System.out.println(this.inventarioServicio.getInventarioById(1));
			
			this.inventarioServicio.deleteInventario(1);
		}catch (Exception e) {
			System.out.println("Error: " + e.getMessage() );
		}
	}
	
	private void rolCRUD() {
		try {
			Rol rol = new Rol();
			rol.setNombre("Cliente");
			this.rolServicio.saveRol(rol);
			Rol rol2 = new Rol();
			rol2.setNombre("Admin");
			this.rolServicio.saveRol(rol2);
			
			List<Rol> rols = rolServicio.getRol();
			for (Rol r : rols) {
				System.out.println(r);	
			}
			System.out.println("*** ROL ESPECIFICO ***");
			System.out.println("*** " + this.rolServicio.getRolById(1) + " ***");
			
			rol2.setRolId(2);
			rol2.setNombre("Administrador");
			this.rolServicio.updateRol(rol2);
			System.out.println("*** " + this.rolServicio.getRolById(2) + " ***");
			
			this.rolServicio.deleteRol(2);
		}catch(Exception e) {
			System.out.println("Error: " + e.getMessage() );
		}
	}
	
	private void usuarioCRUD() {
		try {
			Usuario usuario = new Usuario();
			usuario.setNombre("Wilson Cajisaca");
			usuario.setCorreo("Wilson@gmail.com");
			usuario.setContrasenia("12345");
			usuario.setRol(this.rolServicio.getRolById(1));
			usuarioServicio.saveUsuario(usuario);
			
			List<Usuario> usuarios = this.usuarioServicio.getUsuario();
			for (Usuario usu : usuarios) {
				System.out.println("***" + usu + "***");
			}
			
			System.out.println("*** USUARIO ESPECIFICO ***");
			System.out.println(this.usuarioServicio.getUsuarioById(1));
			
			usuario.setUsuarioId(1);
			usuario.setCorreo("wilsoncajisaca@gmail.com");
			this.usuarioServicio.updateUsuario(usuario);
			System.out.println(this.usuarioServicio.getUsuarioById(1));
			
			//this.usuarioServicio.deleteUsuario(1);
		}catch (Exception e) {
			System.out.println("Error: " + e.getMessage() );
		}
	}

	private void pedidoCabeceraCRUD() {
		try {
			PedidoCabecera pedidoCabecera = new PedidoCabecera();
			pedidoCabecera.setUsuario(this.usuarioServicio.getUsuarioById(1));
			pedidoCabecera.setFechaCompra(new Date());
			pedidoCabecera.setTotal(100);
			pedidoCabeceraServicio.savePedidoCabecera(pedidoCabecera);
			
			List<PedidoCabecera> pedidoCabeceras = this.pedidoCabeceraServicio.getPedidoCabecera();
			for (PedidoCabecera ped : pedidoCabeceras) {
				System.out.println("***" + ped + "***");
			}
			
			System.out.println("*** PEDIDO CABECERA ESPECIFICO ***");
			System.out.println(this.pedidoCabeceraServicio.getPedidoCabeceraById(1));
			
			pedidoCabecera.setTotal(150);
			pedidoCabecera.setPedidoCabeceraId(1);
			this.pedidoCabeceraServicio.updatePedidoCabecera(pedidoCabecera);
			System.out.println(this.pedidoCabeceraServicio.getPedidoCabeceraById(1));
			
			//this.pedidoCabeceraServicio.deletePedidoCabecera(1);
		}catch (Exception e) {
			System.out.println("Error: " + e.getMessage() );
		}
	}
	
	private void pedidoDetalleCRUD() {
		try {
			PedidoDetalle pedidoDetalle = new PedidoDetalle();
			pedidoDetalle.setPedidoCabecera(this.pedidoCabeceraServicio.getPedidoCabeceraById(1));
			pedidoDetalle.setCantidad(5);
			pedidoDetalle.setPrecioUnitario(25);
			pedidoDetalle.setProducto(this.productoServicio.getProductoById(1));
			pedidoDetalle.setSubTotal(100);
			pedidoDetalleService.savePedidoDetalle(pedidoDetalle);
			
			List<PedidoDetalle> pedidoDetalles = this.pedidoDetalleService.getPedidoDetalle();
			for (PedidoDetalle ped : pedidoDetalles) {
				System.out.println("***" + ped + "***");
			}
			
			System.out.println("*** PEDIDO DETALLE ESPECIFICO ***");
			System.out.println(this.pedidoDetalleService.getPedidoDetalleById(1));
			
			pedidoDetalle.setSubTotal(125);
			pedidoDetalle.setPedidoDetalleId(1);
			this.pedidoDetalleService.updatePedidoDetalle(pedidoDetalle);
			System.out.println(this.pedidoDetalleService.getPedidoDetalleById(1));
			
			this.pedidoDetalleService.deletePedidoDetalle(1);
		}catch (Exception e) {
			System.out.println("Error: " + e.getMessage() );
		}
	}
}