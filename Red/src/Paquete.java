
public abstract class Paquete {
	
	private Ip origen;
	private Ip destino;
	private int ttl;
	private boolean tratado;
	
	
	public boolean isTratado() {
		return tratado;
	}
	public void setTratado(boolean tratado) {
		this.tratado = tratado;
	}
	
	public Ip getOrigen() {
		return origen;
	}
	public void setOrigen(Ip origen) {
		this.origen = origen;
	}
	public Ip getDestino() {
		return destino;
	}
	public void setDestino(Ip destino) {
		this.destino = destino;
	}
	public int getTtl() {
		return ttl;
	}
	public void setTtl(int ttl) {
		this.ttl = ttl;
	}

}
