
public abstract class Dispositivo {
	protected int puertos;
	protected Dispositivo[] interfaces = new Dispositivo[20];
	protected SistemaOperativo so;
	protected Paquete paqueteactual;
	protected int dispositivosConectados=0;
	
	
	public SistemaOperativo getSO() throws SistemaOperativoFaltanteException {
		if(so!=null) {
			return so;
		} else {
			throw new SistemaOperativoFaltanteException();
		}
		
	}
	
	public void instalarSO(String so) throws SistemaOperativoInvalidoException, DispositivoInvalidoException {
		if(this instanceof Hub) {
			throw new DispositivoInvalidoException();
		} else {
			switch (so) {
				case "Windows":
					if(this instanceof Terminal) {
						Windows windows = new Windows();
						windows.puertos = puertos;
						windows.setDispositivo(this);
						this.so=((Windows) windows);
						break;
					} else {
						throw new SistemaOperativoInvalidoException();
					}
				case "CiscoSo":
					if(this instanceof Router) {
						CiscoSo cso = new CiscoSo();
						cso.puertos=puertos;
						cso.setDispositivo(this);
						this.so=cso;
						break;
					} else {
						throw new SistemaOperativoInvalidoException();
					}
				default:
					throw new SistemaOperativoInvalidoException();
			}
		}
		
	}
	
	

	public void conectar(Dispositivo d) {
		boolean existe=false;
		for(Dispositivo d2 : interfaces) {
			if(d.equals(d2)){
				existe=true;
			}
		}
		if(existe==false) {
			if(dispositivosConectados<puertos) {
				interfaces[dispositivosConectados] = d;
				dispositivosConectados++;
				d.interfaces[d.dispositivosConectados] = this;
				d.dispositivosConectados++;
			} else {
				System.out.println("Puertos ocupados");
			}
		} else {
			System.out.println("Conexion ya existente");
		}
	}
}
	
