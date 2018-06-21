import java.util.ArrayList;
import java.util.List;


public class Router extends Dispositivo {
	private int interfazdefault;
	Dispositivo[] interfaces = new Dispositivo[puertos];
	
	public Router(int puertos) {
		this.puertos=puertos;
		interfazdefault = 0;
		so=null;
	}
	
	public void agregarRuta(Ip dest, int interfaz) throws RutaInvalidaException {		
		//TODO agragado y borrado valido
		Ruta ruta = new Ruta();
		ruta.setDestino(dest);
		ruta.setInterfaz(interfaz);
		if (!so.tablaruteo.contains(ruta)) {
			so.tablaruteo.add(ruta);
		} else {
			throw new RutaInvalidaException();
		}
	}
	
	public void borrarRuta() throws RutaInvalidaException{
		
	}
	
	public void conectar(Dispositivo d) {
		interfaces[dispositivosConectados] = d;
		dispositivosConectados++;
	}
	
	
	
	
}
