package model;

import java.util.Date;

public class MateriaPrima {
	
	

	private Integer  id_materia;
	private Date fechallenado;
	private float grado;
	private String observaciones;
	private String tipomateria;
	private String descripcion;;
	

	public MateriaPrima() {
		super();
		// TODO Auto-generated constructor stub
	}


	public MateriaPrima(Integer id_materia, Date fechallenado, float grado,
			String observaciones, String tipomateria, String descripcion) {
		super();
		this.id_materia = id_materia;
		this.fechallenado = fechallenado;
		this.grado = grado;
		this.observaciones = observaciones;
		this.tipomateria = tipomateria;
		this.descripcion = descripcion;
	}
	
	
	public Integer getId_materia() {
		return id_materia;
	}


	public void setId_materia(Integer id_materia) {
		this.id_materia = id_materia;
	}


	public Date getFechallenado() {
		return fechallenado;
	}


	public void setFechallenado(Date fechallenado) {
		this.fechallenado = fechallenado;
	}


	public float getGrado() {
		return grado;
	}


	public void setGrado(float grado) {
		this.grado = grado;
	}


	public String getObservaciones() {
		return observaciones;
	}


	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}


	public String getTipomateria() {
		return tipomateria;
	}


	public void setTipomateria(String tipomateria) {
		this.tipomateria = tipomateria;
	}


	public String getDescripcion() {
		return descripcion;
	}


	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	
	@Override
	public String toString() {
		return this.descripcion;
	}


	

}
