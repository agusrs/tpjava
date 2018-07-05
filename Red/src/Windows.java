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
	
	public Ip getIp(int i) {
		return ips[i];
	}
	
	public void verIp() {
		for(Ip ip : ips) {
			System.out.println(ip.getIp());
		}
	}
	
	public Ip getDgw() {
		return dgw;
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
			Servicio p2 = new Servicio(p.getDestino(), p.getOrigen(), p.getTtl(), Servicio.tipos.SendMessageWho);
			p2.setMensaje("Nombre: " + this.nombre + ", Version: " + this.version + ", Ips: " + mostrarIps(ips));
			if(p.getOrigen().esMismaRed(p.getDestino())) {
				enviarPaquete(p2);
			} else {
				enviarPaquete(nuevoPaqueteRuteo(p2));
			}
			break;
		case ICMPRequest:
				Servicio p3 = new Servicio(p.getDestino(), p.getOrigen(), p.getTtl(), Servicio.tipos.ICMPResponse);
				if(p.getOrigen().esMismaRed(p.getDestino())) {
					enviarPaquete(p3);
				} else {
					enviarPaquete(nuevoPaqueteRuteo(p3));
				}
			break;
		case ICMPResponse:
				System.out.println("Recibido ICMP desde: " + p.getOrigen().getIp() + " TTL: " + p.getTtl());
			break;
		case SendMessage:
				if(this instanceof Windows) {
					System.out.println("Mensaje de " + p.getOrigen().getIp() + ": ");
					System.out.println(((Servicio) p).getMensaje());
				}
				break;
		case SendMessageWho:
			if(this instanceof Windows) {
				System.out.println("Mensaje de " + p.getDestino().getIp() + ": ");
				System.out.println(((Servicio) p).getMensaje());
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
	

