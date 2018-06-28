
public class Terminal extends Dispositivo {
	private Dispositivo conectado;
	
	public Terminal() {
		so=null;
		dispositivosConectados = 0;
		puertos=1;
	}
	
	public void setConectado(Dispositivo d) {
		conectado=d;
	}
	
}
