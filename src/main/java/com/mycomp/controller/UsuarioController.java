package com.mycomp.controller;

import java.util.List;

import javax.servlet.http.*;
import org.springframework.web.bind.annotation.*;
import com.mycomp.model.*;
import com.mycomp.service.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

	@Autowired
	private UsuarioService ususrvc;

	@Autowired
	private OrdenService ordsrvc;

	BCryptPasswordEncoder passEncode = new BCryptPasswordEncoder();

	@GetMapping("")
	public String listar(Model model) {

		List<Usuario> lstUsuarios = ususrvc.findAll();

		model.addAttribute("lstUsuarios", lstUsuarios);

		return "ModuloUsuarios/ListarUsuario";

	}

	@GetMapping("/login")
	public String loginCliente() {

		return "ModuloCliente/Login";

	}

	@GetMapping("/acceder")
	public String acceder(Usuario usuario, HttpSession session) {

		Usuario user = ususrvc.getFindById(Integer.parseInt(session.getAttribute("idusuario").toString()));

		if (user != null) {

			session.setAttribute("idusuario", user.getIdUsuario());
			session.setAttribute("tipousuario", user.getTipo());
			session.setAttribute("nombreusuario", user.getNombre());

			switch (user.getTipo()) {

			case "ADMIN":

				return "redirect:/admin";

			case "CLIENTE":

				return "redirect:/indexProducto";

			}

		} else {

			System.out.println("Usuario no existe");

			return "ModuloCliente/Login";

		}

		return "redirect:/";
	}

	@GetMapping("/registro")
	public String registrarCliente() {

		return "ModuloCliente/RegistrarCliente";

	}

	// METODO PARA REGISTRAR
	@PostMapping("/save")
	public String save(Usuario usuario) {

		usuario.setTipo("CLIENTE");

		usuario.setPass(passEncode.encode(usuario.getPass()));

		ususrvc.save(usuario);

		return "ModuloCliente/Login";

	}

	@GetMapping("/compras")
	public String obtenerCompras(Model model, HttpSession session) {

		model.addAttribute("sesion", session.getAttribute("idusuario"));

		Usuario usuario = ususrvc.getFindById(Integer.parseInt(session.getAttribute("idusuario").toString()));

		List<Orden> ordenes = ordsrvc.findByidUsuario(usuario);

		model.addAttribute("ordenes", ordenes);

		return "ModuloCliente/Compras";
	}

	@GetMapping("/detalle/{id}")
	public String detalleCompra(@PathVariable Integer id, HttpSession session, Model model) {

		Orden orden = ordsrvc.getFindById(id);

		model.addAttribute("detalles", orden.getDetOrdenList());

		model.addAttribute("sesion", session.getAttribute("idusuario"));

		return "ModuloCliente/DetalleCompras";
	}

	// @RequestMapping(value = "/logout", method = RequestMethod.GET)
	@GetMapping("/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response) {

		/* LLAMAR AL USUARIO AUNTHENTICADO */
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		/* SI EXISTE EL USUARIO AUTHENTICADO */
		if (auth != null)

			new SecurityContextLogoutHandler().logout(request, response, auth);// cerrar sesion

		/* REDIRECCIONAR A LOGIN */
		return "redirect:login?logout";

	}

}
