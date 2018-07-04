import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;



public class Windows extends SistemaOperativo {
	protected int cantips;
	private Ip dgw;
	private Terminal dispositivo;
	
	public Terminal getDispositivo() {
		return dispositivo;
	}
	
	
	public Windows() {
		cantips = 0;
		nombre = "Windows";
		version = 10;
	}
	
	public void agregarIp(Ip ip) {
		ips[cantips] = ip;
		cantips++;
	}
	
	public void agregarIp(Ip ip, int puerto) {
		agregarIp(ip);
	}
	
	public void agregarDgw(Ip dgw) {
		this.dgw=dgw;
	}
	
	public Ip[] getIp() {
		return ips;
	}
	
	public void verIp() {
		for(Ip ip : ips) {
			System.out.println(ip.getIp());
		}
	}
	
	public void recibirPaquete(Paquete p) throws DestinoInvalidoException, SistemaOperativoFaltanteException {
		if(esDestino(p)) {
				procesarPaquete(p);
		} else {
			throw new DestinoInvalidoException();
		}
	}

	public void ping(Ip ipd) throws DestinoInvalidoException, SistemaOperativoFaltanteException {
		Servicio p = new Servicio(ips[0], ipd, 50, Servicio.tipos.ICMPRequest);
		if(ipd.esMismaRed(ips[0])) {
			enviarPaquete(p);
		} else {
			enviarPaquete(nuevoPaqueteRuteo(p));
		}
	}
	
	public void who(Ip destino) throws DestinoInvalidoException, SistemaOperativoFaltanteException {
		Servicio p = new Servicio(ips[0], destino, 50, Servicio.tipos.WHO);
		if (destino.esMismaRed(ips[0])) {
			enviarPaquete(p);
		} else {
			enviarPaquete(nuevoPaqueteRuteo(p));
		}
	}
	
	public void enviarMensaje(Ip destino, String mensaje) throws DestinoInvalidoException, SistemaOperativoFaltanteException {
		Servicio p = new Servicio(ips[0], destino, 50, Servicio.tipos.SendMessage);
		p.setMensaje(mensaje);
		if (destino.esMismaRed(ips[0])) {
			enviarPaquete(p);
		} else {
			enviarPaquete(nuevoPaqueteRuteo(p));
		}
	}
	
	protected void setDispositivo(Dispositivo dispositivo2) {
		dispositivo=(Terminal) dispositivo2;
		
	}
	
	public void procesarPaquete(Paquete p) throws DestinoInvalidoException, SistemaOperativoFaltanteException {
		switch(((Servicio) p).getTipo()) {
		case WHO:
			if(!p.isTratado()) {
				Servicio p2 = new Servicio(p.getOrigen(), p.getDestino(), 50, Servicio.tipos.WHO);
				p.setTratado(true);
				enviarPaquete(p2);
			}
			break;
		case ICMPRequest:
			if(!p.isTratado()) {
				Servicio p3 = new Servicio(p.getDestino(), p.getOrigen(), 50, Servicio.tipos.ICMPResponse);
				if(p.getOrigen().esMismaRed(p.getDestino())) {
					enviarPaquete(p3);
				} else {
					enviarPaquete(nuevoPaqueteRuteo(p3));
				}
			}
			break;
		case ICMPResponse:
			if(!p.isTratado()) {
				System.out.println("Recibido ICMP desde: " + p.getOrigen().getIp() + " TTL: " + p.getTtl());
				p.setTratado(true);
			}
			break;
		case SendMessage:
			if(!p.isTratado()) {
				if(this instanceof Windows) {
					System.out.println(((Servicio) p).getMensaje());
					p.setTratado(true);
				}
			}
			break;
		}
}

	
	public void enviarPaquete(Paquete p) throws DestinoInvalidoException, SistemaOperativoFaltanteException {
		if(dispositivo.interfaces[0] instanceof Hub) {
			((Hub) dispositivo.interfaces[0]).recibirPaquete(p);
		} else {
			dispositivo.interfaces[0].getSO().recibirPaquete(p);
		}
	}
	

	@Override
	public void enviarPaquete(Paquete p, int interfaz) throws DestinoInvalidoException, SistemaOperativoFaltanteException {
		if(interfaz>1) {
			System.out.println("Error, la terminal solo posee una interfaz");
		} else {
			enviarPaquete(p);
		}
		
	}
}
	

