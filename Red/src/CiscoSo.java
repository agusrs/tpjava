
public class CiscoSo extends SistemaOperativo {
	private Router dispositivo;
	
	public CiscoSo() {
		
	}
	
	public int getPuerto(Ip ip) {
		int puerto = 0;
		for(Ruta ruta : tablaruteo) {
			if(ruta.getDestino().esMismaRed(ip)) {
				puerto = ruta.getInterfazsaliente();
			}
		}
		return puerto;
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
				if(esDestino(p)) {
					switch(((Servicio) p).getTipo()) {
					case WHO:
							Servicio p2 = new Servicio(p.getDestino(), p.getOrigen(), p.getTtl(), Servicio.tipos.SendMessage);
							p2.setMensaje("Nombre: " + this.nombre + ", Version: " + this.version + ", Ips: " + mostrarIps(ips) + "Tabla de Ruteo: " + mostrarTabla(tablaruteo));
							enviarPaquete(p2, getPuerto(p2.getDestino()));
						break;
					case ICMPRequest:
							Servicio p3 = new Servicio(p.getDestino(), p.getOrigen(), p.getTtl(), Servicio.tipos.ICMPResponse);
							enviarPaquete(p3, getPuerto(p3.getDestino()));
						break;
					case ICMPResponse:
							System.out.println("Recibido ICMP desde: " + p.getOrigen().getIp() + " TTL: " + p.getTtl());
						break;
					default:
						break;
					}
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
		boolean enviado = false;
		for(Ruta ruta : tablaruteo) {
			if(ipd.esMismaRed(ruta.getDestino())) {
				enviarPaquete(p, ruta.getInterfazsaliente());
				enviado=true;
			}
		}
		if(enviado==false) {
			System.out.println("Destino inaccesible");
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

	@Override
	public void who(Ip destino) {
		System.out.println("Comando invalido para routers");
		
	}

	@Override
	public void enviarMensaje(Ip destino, String mensaje){
		System.out.println("Comando invalido para routers");
		
	}

}
