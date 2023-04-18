package com.mycomp.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.mycomp.model.*;
import com.mycomp.service.*;

@Controller
@RequestMapping("/productos")
public class ProductoController {

	@Autowired
	private ProductoService prodsrvc;

	@Autowired
	private UploadFileService uploadsrvc;

	@Autowired
	private UsuarioService ususrvc;

	@GetMapping("")
	public String listar(Model model) {

		List<Producto> lstProductos = prodsrvc.findAll();

		model.addAttribute("lstProductos", lstProductos);

		return "ModuloProductos/lista-producto";

	}

	@GetMapping("/create")
	public String nuevoProducto(Model model) {

		Producto producto = new Producto();

		model.addAttribute("producto", producto);

		return "ModuloProductos/NuevoProducto";

	}

	@PostMapping("/save")
	public String save(Producto producto, @RequestParam("img") MultipartFile file, HttpSession session)
			throws Exception {

		Usuario u = ususrvc.getFindById(Integer.parseInt(session.getAttribute("idusuario").toString()));

		producto.setIdUsuario(u);

		/* IMAGEN */
		if (producto.getIdProducto() == null) {// CUANDO SE CARGA UN PRODUCTO SIEMPRE EL ID VIENE NULL

			String nomImagen = uploadsrvc.saveImage(file);

			producto.setImagen(nomImagen);

		} else {

			if (file.isEmpty()) { // CUANDO EDITAMOS EL PRODUCTO PERO NO CAMBIAMOS LA IMAGEN

				Producto prod = new Producto();

				prod = prodsrvc.getFindById(producto.getIdProducto());

				producto.setImagen(prod.getImagen());

			} else { // CUANDO SE EDITA TBN LA IMAGEN

				Producto prod = new Producto();

				prod = prodsrvc.getFindById(producto.getIdProducto());

				/* PARA ELIMINAR CUANDO NO SE LA IMAGEN POR DEFECTO */
				if (!prod.getImagen().equals("default.jpg")) {

					uploadsrvc.deleteImage(prod.getImagen());

				}

				String nomImagen = uploadsrvc.saveImage(file);

				producto.setImagen(nomImagen);

			}

		}

		prodsrvc.save(producto);

		return "redirect:/productos";

	}

	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, Model model) {

		Producto producto = prodsrvc.getFindById(id);

		model.addAttribute("producto", producto);

		return "ModuloProductos/NuevoProducto";

	}

	@GetMapping("/delete/{id}")
	public String delete(@PathVariable Integer id) {

		Producto peli = new Producto();

		peli = prodsrvc.getFindById(id);

		/* PARA ELIMINAR CUANDO NO SE LA IMAGEN POR DEFECTO */
		if (!peli.getImagen().equals("default.jpg")) {

			uploadsrvc.deleteImage(peli.getImagen());

		}

		prodsrvc.delete(id);

		return "redirect:/productos";

	}

}
