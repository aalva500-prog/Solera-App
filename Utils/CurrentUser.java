package Utils;

import model.Usuario;

public class CurrentUser {
	// Clase responsable de conocer el usuario que está autenticado en la aplicación
	private Usuario sessionUser = null;
    static CurrentUser currentU;
	
    private CurrentUser() {
		super();
		// TODO Auto-generated constructor stub
	}
    
    public static CurrentUser getCurrentUser()
    {
    	if(currentU == null)
    		currentU = new CurrentUser();
    	return currentU;
    }

	public Usuario getSessionUser() {
		return sessionUser;
	}

	public void setSessionUser(Usuario sessionUser) {
		this.sessionUser = sessionUser;
	} 

	
}
