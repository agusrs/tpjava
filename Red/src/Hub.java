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
		for (Dispositivo x : interfaces) {
			try {
				x.getSO().recibirPaquete(p1);
			} catch (SistemaOperativoFaltanteException e) {
				System.out.println("El dispositivo: " + x + "no tiene un sistema operativo instalado");
			} catch (DestinoInvalidoException e) {
			}
		}
	}
}
