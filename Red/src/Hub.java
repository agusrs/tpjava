import java.util.ArrayList;
import java.util.Arrays;

public class Hub extends Dispositivo {
	
	public Hub() {
		so=null;
		paqueteactual=null;
		dispositivosConectados=0;
		puertos=2;
	}
	
	public Hub(int cantp) {
		so=null;
		paqueteactual=null;
		puertos = cantp;
		dispositivosConectados=0;
	}
	
	public void recibirPaquete(Paquete p1) {
		for (int i=0;i<dispositivosConectados;i++) {
			try {
				interfaces[i].getSO().recibirPaquete(p1);
			} catch (SistemaOperativoFaltanteException e) {
				System.out.println("El dispositivo: " + interfaces[i] + "no tiene un sistema operativo instalado");
			} catch (DestinoInvalidoException e) {
			}
		}
	}
}
