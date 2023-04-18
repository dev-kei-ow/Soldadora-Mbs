package com.mycomp.controller;

import java.util.*;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.mycomp.global.*;
import com.mycomp.model.*;
import com.mycomp.service.*;

@Controller
@RequestMapping("/")
public class HomeController {

	@Autowired
	private ProductoService prodsrvc;

	@Autowired
	private UsuarioService ususrvc;

	@Autowired
	private OrdenService ordsrvc;

	@Autowired
	private DetalleOrdenService detordsrvc;

	/* ALMACENAR LOS DATOS DE LA ORDEN */
	Orden orden = new Orden();

	@GetMapping("")
	public String home() {

		return "ModuloCliente/home";

	}

	@GetMapping("indexProducto")
	public String index(Model model, HttpSession session) {

		session.getAttribute("idusuario");

		List<Producto> productos = prodsrvc.findAll();

		model.addAttribute("lstProductos", productos);
		model.addAttribute("CartCount", GlobalData.detOrden.size());

		return "ModuloCliente/index";

	}

	@GetMapping("productodetalle/{id}")
	public String productoDetalle(@PathVariable Integer id, Model model) {

		Producto detproducto = prodsrvc.getFindById(id);

		model.addAttribute("detproducto", detproducto);
		model.addAttribute("CartCount", GlobalData.detOrden.size());

		return "ModuloCliente/producto-detalle";

	}

	@PostMapping("addToCart")
	public String agregarCarrito(@RequestParam Integer id, @RequestParam int cantidad, Model model) {

		GlobalData.prods.add(prodsrvc.getFindById(id));

		DetalleOrden cart = new DetalleOrden();
		Producto producto = prodsrvc.getFindById(id);
		double sumaTotal = 0;

		cart.setCantidad(cantidad);
		cart.setPrecio(producto.getPrecio());
		cart.setNombre(producto.getNombre());
		cart.setPrecioTotal(producto.getPrecio() * cantidad);
		cart.setIdProducto(producto);

		/*::VALIDAR QUE EL PRODUCTO NO SE AÑADA DOS VECES::*/
		Integer idProd = producto.getIdProducto(); // AQUI VIENE EL ID 1

		/*
		 * detOrden = CARRITO. SI EN LA LISTA DE DETALLES EXISTE EL ID 1, DEVUELVE TRUE
		 */
		boolean ingresado = GlobalData.detOrden.stream().anyMatch(p -> p.getIdProducto().getIdProducto() == idProd);

		if (!ingresado) {

			/* AÑADIR CADA PRODUCTO Y CADA DETALLE DE ORDEN */
			GlobalData.detOrden.add(cart);
		}

		/* SUMA TODOS LOS TOTALES DEL PRODUCTO DE LA LISTA */
		sumaTotal = GlobalData.detOrden.stream().mapToDouble(DetalleOrden::getPrecioTotal).sum();

		orden.setTotal(sumaTotal);

		model.addAttribute("cart", GlobalData.detOrden);
		model.addAttribute("orden", orden);
		model.addAttribute("CartCount", GlobalData.detOrden.size());

		// return "ModuloCliente/Home";
		return "redirect:/indexProducto";

	}

	@GetMapping("cartGet")
	public String cartGet(Model model) {

		model.addAttribute("cart", GlobalData.detOrden);
		model.addAttribute("orden", orden);
		model.addAttribute("CartCount", GlobalData.detOrden.size());

		return "ModuloCliente/carrito";

	}

	@GetMapping("cart/removeItem/{id}")
	public String cartItemRemove(@PathVariable Integer id, Model model) {

		/* LISTA NUEVA DE PRODUCTOS */
		List<DetalleOrden> ordenNueva = new ArrayList<DetalleOrden>();

		for (DetalleOrden detalleOrden : GlobalData.detOrden) {

			if (detalleOrden.getIdProducto().getIdProducto() != id) {

				ordenNueva.add(detalleOrden);

			}

		}

		/* PONER LA NUEVA LISTA CON LOS PRODUCTOS RESTANTES */
		GlobalData.detOrden = ordenNueva;

		double sumaTotal = 0;

		sumaTotal = GlobalData.detOrden.stream().mapToDouble(DetalleOrden::getPrecioTotal).sum();

		orden.setTotal(sumaTotal);

		model.addAttribute("cart", GlobalData.detOrden);
		model.addAttribute("orden", orden);

		model.addAttribute("CartCount", GlobalData.detOrden.size());

		return "ModuloCliente/Carrito";

	}

	@GetMapping("order")
	public String order(Model model, HttpSession session) {

		Usuario usuario = ususrvc.getFindById(Integer.parseInt(session.getAttribute("idusuario").toString()));

		model.addAttribute("cart", GlobalData.detOrden);
		model.addAttribute("orden", orden);
		model.addAttribute("usuario", usuario);

		return "ModuloCliente/orden-compra";

	}

	/* GUARDAR LA ORDEN */
	@GetMapping("saveOrder")
	public String saveOrder(HttpSession session) {
		Date fechaCreacion = new Date();

		orden.setFechaCreacion(fechaCreacion);
		orden.setFechaRecibida(fechaCreacion);
		orden.setNumero(ordsrvc.generarNumeroOrden());

		// MetodoPago mtpago = metpagosrvc.findbyid
		// USUARIO
		Usuario usuario = ususrvc.getFindById(Integer.parseInt(session.getAttribute("idusuario").toString()));

		orden.setIdUsuario(usuario);

		ordsrvc.save(orden);

		/* GUARDAR DETALLES AL CARRITO */
		for (DetalleOrden dcart : GlobalData.detOrden) {

			dcart.setIdOrden(orden);
			detordsrvc.save(dcart);

		}

		/* LIMPIAR LISTACART Y ORDEN */
		orden = new Orden();

		GlobalData.detOrden.clear();

		return "redirect:/";
	}

}
