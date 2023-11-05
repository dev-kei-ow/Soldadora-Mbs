package com.mycomp.restcontroller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mycomp.model.LineChart;
import com.mycomp.repository.ProductoRepository;
import com.mycomp.repository.UsuarioRepository;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardRestController {

	@Autowired
	private ProductoRepository prodrepo;

	@Autowired
	private UsuarioRepository usurepo;

	@GetMapping("/stadistics")
	public HashMap<String, Object> getDashboardStadistics() {

		long lProdCount = prodrepo.count();
		long lUsuCount = usurepo.count();

		// Crear una lista de objetos LineChart y configurarlos
		List<LineChart> lineCharts = new ArrayList<>();

		lineCharts.add(new LineChart("Ventas", Arrays.asList(31, 40, 28, 51, 42, 82, 56)));
		lineCharts.add(new LineChart("Productos", Arrays.asList(11, 32, 45, 32, 34, 52, 41)));
		lineCharts.add(new LineChart("Usuarios", Arrays.asList(15, 11, 32, 18, 9, 24, 11)));

		// Crear el mapa de respuesta
		HashMap<String, Object> map = new HashMap<>();

		map.put("countProds", lProdCount);
		map.put("countUsus", lUsuCount);
		map.put("lineChart", lineCharts);

		return map;
	}

}
