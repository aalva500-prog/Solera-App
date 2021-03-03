package Utils;

public class Idioma {
	private String idioma; 
	private static Idioma instance = null;
	
	private Idioma (){
		this.idioma = "espanol";
	}
	
	public static Idioma getInstance(){
		if(instance == null){
			instance = new Idioma();
		}
		return instance;
	}
	
	public String getIdioma() {
		return idioma;
	}
	
	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}
}
