import java.util.ArrayList;
import java.util.List;

public class CiscoSo extends SistemaOperativo {
	private Router dispositivo;
	
	public CiscoSo() {
		
	}
	
	public Router getDispositivo() {
		return dispositivo;
	}
	
	public void agregarIp(Ip ip, int puerto) {
		if(puerto<=puertos) {
			ips[puerto-1] = ip;
		} else {
			System.out.println("puerto inexistente");
		}
	}
	
	public void agregarIp(Ip ip) {
		System.out.println("Error, debe especificar un puerto");
	}
	
	
	public void recibirPaquete(Ruteo p) throws DestinoInvalidoException, SistemaOperativoFaltanteException {
		if(esDestino(p)) {
			p.setTtl(p.getTtl()-1);
			if(p.getTtl()>0) {
				procesarPaquete(p);
			}
		} else {
			
		}
	}

	@Override protected void procesarPaquete(Paquete p) throws DestinoInvalidoException, SistemaOperativoFaltanteException {
			if(p instanceof Servicio) {
				super.procesarPaquete(p);
			} else {
				if(esDestino(p)) {
						p.setTtl(p.getTtl()-1);
						if(p.getTtl()>0) {
							Paquete p2 = ((Ruteo) p).getContenido();
							boolean x = false;
							for(Ruta ruta : tablaruteo) {
								if(p2.getDestino().esMismaRed(ruta.getDestino())) {
									enviarPaquete(p2, ruta.getInterfazsaliente());
									x=true;
								}
							}
							if(x=false) {
								enviarPaquete(nuevoPaqueteRuteo(p), getDispositivo().getInterfazdefault());
							}	
						}	
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
		this.getDispositivo().interfaces[puerto].getSO().recibirPaquete(p);
	}
	
	
	public void enviarPaquete(Paquete p) throws DestinoInvalidoException, SistemaOperativoFaltanteException {
		System.out.println("Error, debe indicar una interfaz de salida");
	}
	
}
