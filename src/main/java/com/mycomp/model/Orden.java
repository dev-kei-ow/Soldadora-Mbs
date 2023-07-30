package com.mycomp.model;

import java.io.Serializable;

import java.util.*;

import javax.persistence.*;

@Entity
@Table(name = "orden")
public class Orden implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idOrden;

	private String numero;
	private Date fechaCreacion;
	private Date fechaRecibida;
	private double total;

	/* MUCHAS ORDENES - UN USUARIO */
	@JoinColumn(name = "idUsuario", referencedColumnName = "idUsuario")
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	private Usuario idUsuario;

	@OneToMany(mappedBy = "idOrden", cascade = CascadeType.ALL)
	private List<DetalleOrden> detOrdenList;

	public Orden() {

	}

	public Orden(Integer idOrden, String numero, Date fechaCreacion, Date fechaRecibida, double total,
			Usuario idUsuario, List<DetalleOrden> detOrdenList) {

		this.idOrden = idOrden;
		this.numero = numero;
		this.fechaCreacion = fechaCreacion;
		this.fechaRecibida = fechaRecibida;
		this.total = total;
		this.idUsuario = idUsuario;
		this.detOrdenList = detOrdenList;
	}

	public Integer getIdOrden() {
		return idOrden;
	}

	public void setIdOrden(Integer idOrden) {
		this.idOrden = idOrden;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Date getFechaRecibida() {
		return fechaRecibida;
	}

	public void setFechaRecibida(Date fechaRecibida) {
		this.fechaRecibida = fechaRecibida;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public Usuario getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Usuario idUsuario) {
		this.idUsuario = idUsuario;
	}

	public List<DetalleOrden> getDetOrdenList() {
		return detOrdenList;
	}

	public void setDetOrdenList(List<DetalleOrden> detOrdenList) {
		this.detOrdenList = detOrdenList;
	}

}
