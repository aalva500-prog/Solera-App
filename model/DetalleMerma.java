package model;

import java.util.Date;

public class DetalleMerma {
	
	public Integer getAnejo() {
		return anejo;
	}

	public void setAnejo(Integer anejo) {
		this.anejo = anejo;
	}

	public Integer getId_detallemerma() {
		return id_detallemerma;
	}

	public void setId_detallemerma(Integer id_detallemerma) {
		this.id_detallemerma = id_detallemerma;
	}

	public String getMateria() {
		return materia;
	}

	public void setMateria(String materia) {
		this.materia = materia;
	}

	public Integer getCanttoneles() {
		return canttoneles;
	}

	public void setCanttoneles(Integer canttoneles) {
		this.canttoneles = canttoneles;
	}

	public Integer getTamano() {
		return tamano;
	}

	public void setTamano(Integer tamano) {
		this.tamano = tamano;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public float getPerdida() {
		return perdida;
	}

	public void setPerdida(float perdida) {
		this.perdida = perdida;
	}

	public Integer getExistencia() {
		return existencia;
	}

	public void setExistencia(Integer existencia) {
		this.existencia = existencia;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getMes() {
		return mes;
	}

	public void setMes(String mes) {
		this.mes = mes;
	}

	private Integer anejo;
	private Integer id_detallemerma;
	private String materia;
	private Integer canttoneles;
	private Integer tamano;
	private Integer total;
	private float perdida;
	private Integer existencia;
	private Date fecha;
	private String mes;
	
	public DetalleMerma(Integer anejo, Integer id_detallemerma,
			String materia, Integer canttoneles, Integer tamano, Integer total,
			float perdida, Integer existencia, Date fecha, String mes) {
		super();
		this.anejo = anejo;
		this.id_detallemerma = id_detallemerma;
		this.materia = materia;
		this.canttoneles = canttoneles;
		this.tamano = tamano;
		this.total = total;
		this.perdida = perdida;
		this.existencia = existencia;
		this.fecha = fecha;
		this.mes = mes;
	}

	public DetalleMerma() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
