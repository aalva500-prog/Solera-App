package model;




public class Usuario {

	private String username = null;
	private String password = null; 	
	private String nombre = null;
	private String rol = null;// es el identificador del rol
	
	
		
	
	public Usuario(String username, String rol) {
		super();
		this.username = username;
		this.rol = rol;
		this.password = "";
		}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Usuario() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
}
