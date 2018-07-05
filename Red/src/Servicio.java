
public class Servicio extends Paquete {
	public enum tipos {WHO, ICMPRequest, ICMPResponse, SendMessage, SendMessageWho}
	private tipos tipo;
	private String mensaje;
	
	public Servicio (Ip ipo, Ip ipd, int ttl, tipos tipo){
		setDestino(ipd);
		setOrigen(ipo);
		setTtl(ttl);
			switch (tipo) {
			case WHO:
				this.tipo = tipos.WHO;
				break;
			case ICMPRequest:
				this.tipo = tipos.ICMPRequest;
				break;
			case ICMPResponse:
				this.tipo = tipos.ICMPResponse;
				break;
			case SendMessage:
				this.tipo = tipos.SendMessage;
				break;
			case SendMessageWho:
				this.tipo = tipos.SendMessageWho;
				break;
			}
	}
	
	public void setMensaje(String mensaje) {
		this.mensaje=mensaje;
	}
	
	public String getMensaje() {
		return mensaje;
	}
	
	public tipos getTipo() {
		return tipo;
	}
	
	public void setTipo(tipos tipo) {
		switch (tipo) {
		case WHO:
			this.tipo = tipos.WHO;
			break;
		case ICMPRequest:
			this.tipo = tipos.ICMPRequest;
			break;
		case ICMPResponse:
			this.tipo = tipos.ICMPResponse;
			break;
		case SendMessage:
			this.tipo = tipos.SendMessage;
			break;
		case SendMessageWho:
			this.tipo=tipos.SendMessageWho;
			break;
		}
	}
}
