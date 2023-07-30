package com.mycomp.model;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "producto")
public class Producto implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idProducto;

	private String nombre;
	private double precio;
	private String descripcion;
	private String imagen;
	private int cantidad;

	// MUCHOS PRODUCTOS - UN USUARIO CLIENTE
	@JoinColumn(name = "idUsuario", referencedColumnName = "idUsuario")
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	private Usuario idUsuario;

	public Producto() {

	}

	public Producto(Integer idProducto, String nombre, double precio, String descripcion, String imagen, int cantidad,
			Usuario idUsuario) {

		this.idProducto = idProducto;
		this.nombre = nombre;
		this.precio = precio;
		this.descripcion = descripcion;
		this.imagen = imagen;
		this.cantidad = cantidad;
		this.idUsuario = idUsuario;
	}

	public Integer getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(Integer idProducto) {
		this.idProducto = idProducto;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public Usuario getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Usuario idUsuario) {
		this.idUsuario = idUsuario;
	}

}
