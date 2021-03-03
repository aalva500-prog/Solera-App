package model;




public class Rol {

	private String descripcion;
	private String rol;
	private boolean cancelado;

	
	public Rol() {
		super();
	}
	
	
	public Rol(String rol) {
		super();
		this.rol = rol;
	}
	
	

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}
	
	public boolean isCancelado() {
		return cancelado;
	}
	public void setCancelado(boolean cancelado) {
		this.cancelado = cancelado;
	}

	@Override
	public String toString() {
		return this.descripcion;
	}
	
	
}
