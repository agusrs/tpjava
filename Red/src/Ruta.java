
public class Ruta {
	private Ip destino;
	private int interfazsaliente;
	
	public void setDestino(Ip red) {
		destino = red;
	}
	
	public void setInterfaz(int interfaz) {
		interfazsaliente=interfaz;
	}
	
	public Ip getDestino() {
		return destino;
	}
	public int getInterfazsaliente() {
		return interfazsaliente;
	}
	
	
}
