
public abstract class SistemaOperativo {
	private Paquete paquetellegada;
	protected Ip ipdispositivo;
	
	
	public void enviarPaquete(Paquete p) {}
	public void recibirPaquete(Paquete p) {
		paquetellegada=p;
	}
}
