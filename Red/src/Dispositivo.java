
public abstract class Dispositivo {
	protected int puertos;
	protected Dispositivo[] interfaces = new Dispositivo[puertos];
	protected SistemaOperativo so;
	protected Paquete paqueteactual;
	protected int dispositivosConectados;
	
	public SistemaOperativo getSO() throws SistemaOperativoFaltanteException {
		if(so!=null) {
			return so;
		} else {
			throw new SistemaOperativoFaltanteException();
		}
		
	}
	
	public void instalarSO(String so) throws SistemaOperativoInvalidoException {
		if(this instanceof Hub) {
			System.out.println("No puede instalar un SO en un Hub");
		} else {
			switch (so) {
				case "Windows":
					Windows windows = new Windows();
					windows.puertos = puertos;
					this.so=windows;
					break;
				case "CiscoSo":
					CiscoSo cso = new CiscoSo();
					cso.puertos=puertos;
					this.so=cso;
					break;
				default:
					throw new SistemaOperativoInvalidoException();
			}
		}
		
	}
	

	public void conectar(Dispositivo d) {
		interfaces[dispositivosConectados] = d;
		dispositivosConectados++;
		d.interfaces[dispositivosConectados] = this;
		d.dispositivosConectados++;
		
	}
}
	
