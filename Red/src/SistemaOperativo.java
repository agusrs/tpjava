import java.util.ArrayList;
import java.util.List;

public abstract class SistemaOperativo {
	protected String nombre;
	protected double version;
	protected Paquete paquete;
	protected int puertos;
	protected Ip[] ips = new Ip[puertos];
	protected Ip dgw;
	protected List<Ruta> tablaruteo;
	
	public SistemaOperativo() {
		tablaruteo = new ArrayList<>();
	}
	
	public void ping(Ip ipd) {
		while(!ipd.esIpValida()) {
			System.out.println("Direccion ip invalida");
			System.out.println("Ingrese nuevamente: ");
			//ipd = teclado.nextline();
		}
		
		Servicio p = new Servicio(ips[0], ipd, 50, Servicio.tipos.WHO);
		
		if (ipd.esMismaRed(ips[0])) {
			enviarPaquete(p);
		} else {
			enviarPaquete(nuevoPaqueteRuteo(p));
		}
	}
	
	public Ruteo nuevoPaqueteRuteo(Paquete p) {
		Ruteo pqt = new Ruteo(p);
		return pqt;
	}
	
	public void recibirPaquete(Paquete p) throws PackageTypeException {
		
	}
	
	public void enviarPaquete(Paquete p) {
		
	}
	
	public boolean esDestino(Paquete p) {
		boolean res = false;
		for (Ip ip:ips) {
			if(p.destino.equals(ip)) {
				res=true;
			}
		}
		return res;
	}
}
