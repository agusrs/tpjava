
public class Servicio extends Paquete {
	enum tipos {WHO, ICMPRequest, ICMPResponse, SendMessage}
	tipos tipo;
	
	public Servicio (Ip ipd, Ip ipo, int ttl, String tipo){
		destino = ipd;
		origen = ipo;
		this.ttl=ttl;
			switch (tipo) {
			case("WHO"):
				this.tipo = tipos.WHO;
				break;
			case("ICMPRequest"):
				this.tipo = tipos.ICMPRequest;
				break;
			case("ICMPResponse"):
				this.tipo = tipos.ICMPResponse;
				break;
			case("SendMessage"):
				this.tipo = tipos.SendMessage;
				break;
			}
}
}
