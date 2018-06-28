import java.util.ArrayList;
import java.util.List;

public abstract class SistemaOperativo {
	protected String nombre;
	protected double version;
	protected Paquete paquete;
	protected int puertos;
	protected List<Ruta> tablaruteo;
	protected Ip[] ips = new Ip[100];
	
	public SistemaOperativo() {
		tablaruteo = new ArrayList<>();
	}	
	

	public Ruteo nuevoPaqueteRuteo(Paquete p) {
		Ruteo pqt = new Ruteo(p);
		return pqt;
	}
	
	public abstract void ping(Ip ipd) throws DestinoInvalidoException, SistemaOperativoFaltanteException;
	
	public abstract void enviarPaquete(Paquete p) throws DestinoInvalidoException, SistemaOperativoFaltanteException;
	public abstract void enviarPaquete(Paquete p, int interfaz) throws DestinoInvalidoException, SistemaOperativoFaltanteException;

	
	protected void procesarPaquete(Paquete p) throws DestinoInvalidoException, SistemaOperativoFaltanteException {
			switch(((Servicio) p).getTipo()) {
			case WHO:
				Servicio p2 = new Servicio(p.getOrigen(), p.getDestino(), 50, Servicio.tipos.WHO);
				enviarPaquete(p2);
				break;
			case ICMPRequest:
				Servicio p3 = new Servicio(p.getDestino(), p.getOrigen(), 50, Servicio.tipos.ICMPResponse);
				enviarPaquete(p3);
				break;
			case ICMPResponse:
				System.out.println("Recibido ICMP desde: " + p.getOrigen().getIp() + " TTL: " + p.getTtl());
				break;
			case SendMessage:
				if(this instanceof Windows) {
					System.out.println(((Servicio) p).getMensaje());
				}
				break;
			}
	}
			

	public abstract void agregarIp(Ip ip);
	public abstract void agregarIp(Ip ip, int puerto);

	
	public void recibirPaquete(Paquete p) throws DestinoInvalidoException, SistemaOperativoFaltanteException {
		if(esDestino(p)) {
				procesarPaquete(p);
		} else {
			throw new DestinoInvalidoException();
		}
	}
	
	public boolean esDestino(Paquete p) {
		boolean res = false;
		for (Ip ip:ips) {
				if(p.getDestino().equals(ip)) {
					res=true;
				}
			}
		return res;

	}
}
