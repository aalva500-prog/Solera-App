package model;

import java.util.Date;

public class extracciones {
	public String getObservaciones() {
		return observaciones;
	}


	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}


	public Integer getId_extracciones() {
		return id_extracciones;
	}


	public void setId_extracciones(Integer id_extracciones) {
		this.id_extracciones = id_extracciones;
	}


	public Integer getId_materia() {
		return id_materia;
	}


	public void setId_materia(Integer id_materia) {
		this.id_materia = id_materia;
	}


	public Integer getInventario() {
		return inventario;
	}


	public void setInventario(Integer inventario) {
		this.inventario = inventario;
	}


	public Integer getLiqsacado() {
		return liqsacado;
	}


	public void setLiqsacado(Integer liqsacado) {
		this.liqsacado = liqsacado;
	}


	public Integer getAnejo() {
		return anejo;
	}


	public void setAnejo(Integer anejo) {
		this.anejo = anejo;
	}


	public float getPerdida() {
		return perdida;
	}


	public void setPerdida(float perdida) {
		this.perdida = perdida;
	}


	public float getPerdidamensual() {
		return perdidamensual;
	}


	public void setPerdidamensual(float perdidamensual) {
		this.perdidamensual = perdidamensual;
	}


	public float getPerdidaanual() {
		return perdidaanual;
	}


	public void setPerdidaanual(float perdidaanual) {
		this.perdidaanual = perdidaanual;
	}


	public float getPerdidatotal() {
		return perdidatotal;
	}


	public void setPerdidatotal(float perdidatotal) {
		this.perdidatotal = perdidatotal;
	}


	public Date getFecha() {
		return fecha;
	}


	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}


	public float getGrado() {
		return grado;
	}


	public void setGrado(float grado) {
		this.grado = grado;
	}


	private Integer id_extracciones;
	private Integer id_materia;
	private Integer inventario;
	private Integer liqsacado;
	private Integer anejo;
	private float perdida;
	private float perdidamensual;
	private float perdidaanual;
	private float perdidatotal;
	private Date fecha;
	private float grado;
	private String observaciones;
	
	



	public extracciones(Integer id_extracciones, Integer id_materia,
			Integer inventario, Integer liqsacado, Integer anejo,
			float perdida, float perdidamensual, float perdidaanual,
			float perdidatotal, Date fecha, float grado, String observaciones) {
		super();
		this.id_extracciones = id_extracciones;
		this.id_materia = id_materia;
		this.inventario = inventario;
		this.liqsacado = liqsacado;
		this.anejo = anejo;
		this.perdida = perdida;
		this.perdidamensual = perdidamensual;
		this.perdidaanual = perdidaanual;
		this.perdidatotal = perdidatotal;
		this.fecha = fecha;
		this.grado = grado;
		this.observaciones = observaciones;
	}


	public extracciones() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	

}
