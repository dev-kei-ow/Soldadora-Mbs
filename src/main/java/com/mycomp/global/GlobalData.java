package com.mycomp.global;

import java.util.ArrayList;
import java.util.List;

import com.mycomp.model.*;

public class GlobalData {

	public static List<Producto> prods;
	public static List<DetalleOrden> detOrden;
	static {

		prods = new ArrayList<Producto>();

		detOrden = new ArrayList<DetalleOrden>();

	}

}
