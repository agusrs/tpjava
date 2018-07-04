import java.util.ArrayList;
import java.util.List;

public abstract class SistemaOperativo {
	protected String nombre;
	protected double version;
	protected Paquete paquete;
	protected int puertos;
	protected List<Ruta> tablaruteo;
	protected Ip[] ips = new Ip[100];
	
	public SistemaOperativo() {
		tablaruteo = new ArrayList<>();
	}	
	
	public List<Ruta> getTablaRuteo() {
		return tablaruteo;
	}

	public Ruteo nuevoPaqueteRuteo(Paquete p) {
		Ruteo pqt = new Ruteo(p);
		return pqt;
	}
	
	public abstract void ping(Ip ipd) throws DestinoInvalidoException, SistemaOperativoFaltanteException;
	
	public abstract void enviarPaquete(Paquete p) throws DestinoInvalidoException, SistemaOperativoFaltanteException;
	public abstract void enviarPaquete(Paquete p, int interfaz) throws DestinoInvalidoException, SistemaOperativoFaltanteException;

	
	public abstract void procesarPaquete(Paquete p) throws DestinoInvalidoException, SistemaOperativoFaltanteException;
			

	public abstract void agregarIp(Ip ip);
	public abstract void agregarIp(Ip ip, int puerto);
	

	
	public abstract void recibirPaquete(Paquete p) throws DestinoInvalidoException, SistemaOperativoFaltanteException ;
	
	public boolean esDestino(Paquete p) {
		boolean res = false;
		for (Ip ip:ips) {
				if(p.getDestino().equals(ip)) {
					res=true;
				}
			}
		return res;

	}
	
	public String mostrarIps(Ip[] ips) {
		String ipsf = null;
		for(Ip ip : ips) {
			if(ip!=null) {
				ipsf = ipsf + " // " + ip.getIp();
			}
		}
		return ipsf;
	}
	
	public String mostrarTabla(List<Ruta> tablaruteo) {
		String tabla = null;
		for(Ruta ruta : tablaruteo) {
			tabla = tabla + " // " + ruta.getDestino();
		}
		return tabla;
	}
}
