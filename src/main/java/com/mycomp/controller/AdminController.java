package com.mycomp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mycomp.model.*;
import com.mycomp.service.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private ProductoService prodsrvc;

	@Autowired
	private OrdenService ordsrvc;

	@GetMapping("")
	public String index() {

		return "ModuloAdmin/dashboard";
	}
	@GetMapping("/gestiones")
	public String gestiones() {

		return "ModuloAdmin/Gestiones";
	}

	@GetMapping("/home")
	public String home(Model model) {

		List<Producto> productos = prodsrvc.findAll();

		model.addAttribute("lstProductos", productos);

		return "ModuloAdmin/Home";
	}

	@GetMapping("/ordenes")
	public String ordenes(Model model) {

		model.addAttribute("ordenes", ordsrvc.findAll());

		return "ModuloAdmin/Ordenes";
	}

	@GetMapping("/detalle/{id}")
	public String detalleOrden(Model model, @PathVariable Integer id) {

		Orden orden = ordsrvc.getFindById(id);

		model.addAttribute("detalleordenes", orden.getDetOrdenList());

		return "ModuloAdmin/DetalleOrdenes";
	}

}
