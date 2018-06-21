import java.util.ArrayList;
import java.util.List;

public abstract class SistemaOperativo {
	protected Paquete paquete;
	protected int puertos;
	protected Ip[] ips = new Ip[puertos];
	protected Ip dgw;
	protected List<Ruta> tablaruteo;
	
	public SistemaOperativo() {
		tablaruteo = new ArrayList<>();
	}
	
	
	
	public void enviarPaquete(Paquete p) {
		
	}
	public void recibirPaquete(Paquete p) throws PackageTypeException {
	}
	
	public boolean esDestino(Ip destino) {
		boolean res = false;
		for (Ip ip:ips) {
			if(destino.equals(ip)) {
				res=true;
			}
		}
		return res;
	}
}
