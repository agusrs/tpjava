
public class Ruteo extends Paquete {
	private Paquete contenido;

	public Paquete getContenido() {
		return contenido;
	}

	public void setContenido(Paquete contenido) {
		this.contenido = contenido;
	}

	public Ruteo(Paquete p) {
		contenido = p;
		
	}
	
	
}
