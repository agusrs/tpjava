
public abstract class Dispositivo {
	protected Ip dgw;
	protected int puertos;
	protected Ip[] ips = new Ip[puertos];
	protected SistemaOperativo so;
	protected Paquete paqueteactual;
	
	public SistemaOperativo getSO() throws SistemaOperativoFaltanteException {
		if(so!=null) {
			return so;
		} else {
			throw new SistemaOperativoFaltanteException();
		}
		
	}
	
	/*public void recibirPaquete(Paquete p1) {
		paqueteactual=p1;
	}*/
}
