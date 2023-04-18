package com.mycomp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mycomp.model.*;
import com.mycomp.service.*;

@Controller
@RequestMapping("/trabajadores")
public class TrabajadorController {

	@Autowired
	private TrabajadorService trabsrvc;

	@GetMapping("")
	public String listar(Model model) {

		List<Trabajador> lstTrabajador = trabsrvc.findAll();

		model.addAttribute("lstTrabajadores", lstTrabajador);

		return "ModuloTrabajadores/listarTrabajador";

	}

	@GetMapping("/create")
	public String crear() {

		return "ModuloTrabajadores/listarTrabajador";

	}

	@GetMapping("/save")
	public String save() {

		return "ModuloTrabajadores/listarTrabajador";

	}

	@GetMapping("/edit/{id}")
	public String edit() {

		return "ModuloTrabajadores/listarTrabajador";

	}

	@GetMapping("/delete/{id}")
	public String delete() {

		return "ModuloTrabajadores/listarTrabajador";

	}

}
