import java.util.ArrayList;
import java.util.List;

public class CiscoSo extends SistemaOperativo {
	private Router dispositivo;
	
	public CiscoSo() {
		
	}
	
	public Router getDispositivo() {
		return dispositivo;
	}
	
	public void agregarRuta(Ip ip, int interfaz) {
		Ruta r = new Ruta(ip, interfaz);
		this.tablaruteo.add(r);
	}
	
	public void agregarIp(Ip ip, int puerto) {
		if(puerto<=puertos) {
			ips[puerto-1] = ip;
			agregarRuta(ip, puerto);
		} else {
			System.out.println("puerto inexistente");
		}
	}
	
	public void agregarIp(Ip ip) {
		System.out.println("Error, debe especificar un puerto");
	}
	
	
	public void recibirPaquete(Paquete p) throws DestinoInvalidoException, SistemaOperativoFaltanteException {
		for(int i=0; ips[i]!=null;i++) {
			if(p.getDestino().esMismaRed(ips[i])) {
				p.setTtl(p.getTtl()-1);
				if(p.getTtl()>0) {
					procesarPaquete(p);
				}
			}
		}
	}

	@Override
	public void procesarPaquete(Paquete p) throws DestinoInvalidoException, SistemaOperativoFaltanteException {
			if(p instanceof Servicio) {
				switch(((Servicio) p).getTipo()) {
				case WHO:
					if(!p.isTratado()) {
						Servicio p2 = new Servicio(p.getOrigen(), p.getDestino(), 50, Servicio.tipos.SendMessage);
						p2.setMensaje("Nombre: " + this.nombre + ", Version: " + this.version + ", Ips: " + mostrarIps(ips) + "Tabla de Ruteo: " + mostrarTabla(tablaruteo));
						p.setTratado(true);
						enviarPaquete(p2);
					}
					break;
				case ICMPRequest:
					if(!p.isTratado()) {
						Servicio p3 = new Servicio(p.getDestino(), p.getOrigen(), 50, Servicio.tipos.ICMPResponse);
						enviarPaquete(p3);
					}
					break;
				case ICMPResponse:
					if(!p.isTratado()) {
						System.out.println("Recibido ICMP desde: " + p.getOrigen().getIp() + " TTL: " + p.getTtl());
						p.setTratado(true);
					}
					break;
				}
			} else {
				Paquete p2 = ((Ruteo) p).getContenido();
				boolean x = false;
				for(Ruta ruta : tablaruteo) {
					if(p2.getDestino().esMismaRed(ruta.getDestino())) {
						enviarPaquete(p2, ruta.getInterfazsaliente());
						x=true;
					}
				}
				if(x=false) {
					enviarPaquete(nuevoPaqueteRuteo(p2), getDispositivo().getInterfazdefault());
				}	
					
			}
	}

	public void ping(Ip ipd) throws DestinoInvalidoException, SistemaOperativoFaltanteException {		
		Servicio p = new Servicio(ips[0], ipd, 50, Servicio.tipos.ICMPRequest);	
		for(Ruta ruta : tablaruteo) {
			if(ipd.esMismaRed(ruta.getDestino())) {
				enviarPaquete(p, ruta.getInterfazsaliente());
			} else {
				System.out.println("Destino inaccesible");
			}
		}
	}

	protected void setDispositivo(Dispositivo dispositivo2) {
		dispositivo=(Router) dispositivo2;
		
	}

	public void enviarPaquete(Paquete p, int puerto) throws DestinoInvalidoException, SistemaOperativoFaltanteException {
		Dispositivo d = this.getDispositivo().interfaces[puerto-1];
		if (d instanceof Hub) {
			((Hub) d).recibirPaquete(p);
		} else {
			//d.getSO().recibirPaquete(p);
		}
	}
	
	
	public void enviarPaquete(Paquete p) throws DestinoInvalidoException, SistemaOperativoFaltanteException {
		System.out.println("Error, debe indicar una interfaz de salida");
	}
	
}
