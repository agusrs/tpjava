import java.util.ArrayList;
import java.util.List;


public class Router extends Dispositivo {
	private int interfazdefault;
	
	public int getInterfazdefault() {
		return interfazdefault;
	}

	public void setInterfazdefault(int interfazdefault) {
		this.interfazdefault = interfazdefault;
	}

	public Router() {
		puertos=2;
		so=null;
	}
	
	public Router(int puertos) {
		this.puertos=puertos;
		so=null;
	}
	
	public void borrarRuta() throws RutaInvalidaException{
		
	}
	

	
	
	
	
}
