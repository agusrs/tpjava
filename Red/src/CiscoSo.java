import java.util.ArrayList;
import java.util.List;

public class CiscoSo extends SistemaOperativo {
	
	public CiscoSo() {
	}
	
	
	public void recibirPaquete(Paquete p) throws PackageTypeException {
		if(esDestino(p.destino)) {
			paquete=p;
			if(p instanceof Servicio && ((Servicio) p).tipo == Servicio.tipos.SendMessage) {
				throw new PackageTypeException();
			}
		} else {
			enviarPaquete(p);
		}
	}
	//
	public void Ping(Ip destino) {
		if(ips[0].esMismaRed(destino)) {
			for(Ruta ruta : tablaruteo) {
				
			}
		}
	}
}
