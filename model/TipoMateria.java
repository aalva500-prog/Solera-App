package model;

public class TipoMateria {
	private Integer id_tipomateria;
	private String descripcion;
	
	
	public TipoMateria(String descripcion) {
		super();
		this.descripcion = descripcion;
	}


	public TipoMateria() {
		super();
		}


	public Integer getId_tipomateria() {
		return id_tipomateria;
	}


	public void setId_tipomateria(Integer id_tipomateria) {
		this.id_tipomateria = id_tipomateria;
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
