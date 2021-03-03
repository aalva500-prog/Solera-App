package model;

public class Mes {
	@Override
	public String toString() {
		return this.descripcion;
	}

	public Integer getID() {
		return ID;
	}

	public void setID(Integer iD) {
		ID = iD;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	private Integer ID;
	private String descripcion;
	
	public Mes() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Mes(Integer iD, String descripcion) {
		super();
		ID = iD;
		this.descripcion = descripcion;
	}
	
	

}
