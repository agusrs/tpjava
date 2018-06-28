import java.util.ArrayList;
import java.util.List;

public class CiscoSo extends SistemaOperativo {
	private Router dispositivo;
	
	public CiscoSo() {
		
	}
	
	public Router getDispositivo() {
		return dispositivo;
	}
	
	public void setIp(Ip ip, int puerto) {
		if(puerto<=puertos) {
			ips[puerto] = ip;
		} else {
			System.out.println("puerto inexistente");
		}
	}
	
	
	public void recibirPaquete(Paquete p) throws DestinoInvalidoException {
		//
	}
	//
	
	public void enviarPaquete(Paquete p, int puerto) throws DestinoInvalidoException, SistemaOperativoFaltanteException {
		this.getDispositivo().interfaces[puerto].getSO().recibirPaquete(p);
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
	
}
