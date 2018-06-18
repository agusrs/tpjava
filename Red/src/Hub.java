import java.util.ArrayList;
import java.util.Arrays;

public class Hub extends Dispositivo {
	int dispositivosConectados;
	Dispositivo[] Puertos = new Dispositivo[puertos];
	
	public Hub(int cantp) {
		dgw=null;
		ips=null;
		so=null;
		paqueteactual=null;
		puertos = cantp;
		dispositivosConectados=0;
	}
	
	public void ReenviarPaquete(Paquete p1) {
		for (Dispositivo x : Puertos) {
			try {
				x.getSO().recibirPaquete(p1);
			} catch (SistemaOperativoFaltanteException e) {
				System.out.println("El dispositivo: " + x + "no tiene un sistema operativo instalado");
			}
		}
	}
}
