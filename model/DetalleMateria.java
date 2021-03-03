package model;

public class DetalleMateria {
public String getSeccion() {
		return seccion;
	}


	public void setSeccion(String seccion) {
		this.seccion = seccion;
	}


	public String getUbicacion() {
		return ubicacion;
	}


	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}


	public Integer getTamano() {
		return tamano;
	}


	public void setTamano(Integer tamano) {
		this.tamano = tamano;
	}


	public Integer getTonel() {
		return tonel;
	}


	public void setTonel(Integer tonel) {
		this.tonel = tonel;
	}


	public float getVolumen() {
		return volumen;
	}


	public void setVolumen(float volumen) {
		this.volumen = volumen;
	}


	public String getId_materia() {
		return id_materia;
	}


	public void setId_materia(String id_materia) {
		this.id_materia = id_materia;
	}


	public Integer getId_detalle() {
		return id_detalle;
	}


	public void setId_detalle(Integer id_detalle) {
		this.id_detalle = id_detalle;
	}


private String seccion;
private String ubicacion;
private Integer tamano;
private Integer tonel;
private float volumen;
private String id_materia;
private Integer id_detalle;


public DetalleMateria(String seccion, String ubicacion, Integer tamano,
		Integer tonel, float volumen, String id_materia, Integer id_detalle) {
	super();
	this.seccion = seccion;
	this.ubicacion = ubicacion;
	this.tamano = tamano;
	this.tonel = tonel;
	this.volumen = volumen;
	this.id_materia = id_materia;
	this.id_detalle = id_detalle;
}


public DetalleMateria() {
	super();
	// TODO Auto-generated constructor stub
}



}
