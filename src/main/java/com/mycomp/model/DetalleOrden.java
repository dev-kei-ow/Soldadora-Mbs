package com.mycomp.model;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "detalle_orden")
public class DetalleOrden implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idDetalle;

	private String nombre;
	private int cantidad;
	private double precio;
	private double precioTotal;

	@JoinColumn(name = "idProducto", referencedColumnName = "idProducto")
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	private Producto idProducto;

	@ManyToOne
	@JoinColumn(name = "idOrden", updatable = false, nullable = false)
	private Orden idOrden;

	public DetalleOrden() {

	}

	public DetalleOrden(Integer idDetalle, String nombre, int cantidad, double precio, double precioTotal,
			Producto idProducto, Orden idOrden) {

		this.idDetalle = idDetalle;
		this.nombre = nombre;
		this.cantidad = cantidad;
		this.precio = precio;
		this.precioTotal = precioTotal;
		this.idProducto = idProducto;
		this.idOrden = idOrden;
	}

	public Integer getIdDetalle() {
		return idDetalle;
	}

	public void setIdDetalle(Integer idDetalle) {
		this.idDetalle = idDetalle;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public double getPrecioTotal() {
		return precioTotal;
	}

	public void setPrecioTotal(double precioTotal) {
		this.precioTotal = precioTotal;
	}

	public Producto getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(Producto idProducto) {
		this.idProducto = idProducto;
	}

	public Orden getIdOrden() {
		return idOrden;
	}

	public void setIdOrden(Orden idOrden) {
		this.idOrden = idOrden;
	}

}
