
public class Servicio extends Paquete {
	enum tipos {WHO, ICMPRequest, ICMPResponse, SendMessage}
	tipos tipo;
	
	public Servicio (String ipd, String ipo, int ttl, String tipo) {
		destino = ipd;
		origen = ipo;
		this.ttl=ttl;
		try {
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
			default:
				throw new PackageTypeException();
		
		
			}
		} catch (PackageTypeException e) {
			System.out.println("Tipo de servicio invalido");
		}

}
}
