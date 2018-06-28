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
	
	public void who(Ip destino) {
		Servicio p = new Servicio(dgw, destino, 50, Servicio.tipos.WHO);
		if (destino.esMismaRed(dgw)) {
			enviarPaquete(p);
		} else {
			enviarPaquete(nuevoPaqueteRuteo(p));
		}
	}
	
	public void enviarMensaje(Ip destino, String mensaje) {
		Servicio p = new Servicio(dgw, destino, 50, Servicio.tipos.SendMessage);
		p.setMensaje(mensaje);
		if (destino.esMismaRed(dgw)) {
			enviarPaquete(p);
		} else {
			enviarPaquete(nuevoPaqueteRuteo(p));
		}
	}
	
	protected void setDispositivo(Dispositivo dispositivo2) {
		dispositivo=(Terminal) dispositivo2;
		
	}
}
	

