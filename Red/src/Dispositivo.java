
public abstract class Dispositivo {
	protected Ip dgw;
	protected int puertos;
	protected Ip[] ips = new Ip[puertos];
	protected String so = "-";
	protected Paquete paqueteactual;
	
	public String getSO() throws SistemaOperativoFaltanteException {
		if(so!="-") {
			return so;
		} else {
			throw new SistemaOperativoFaltanteException();
		}
		
	}
	
	public void recibirPaquete(Paquete p1) {
		paqueteactual=p1;
	}
}
