import java.util.ArrayList;
import java.util.Arrays;

public class Hub {
	int cantidadPuertos;
	int dispositivosConectados;
	Dispositivo[] Puertos = new Dispositivo[cantidadPuertos];
	
	public Hub() {
		dispositivosConectados=0;
	}
	
	public void ReenviarPaquete(Paquete p1) {
		for (Dispositivo x : Puertos) {
			x.recibirPaquete(p1);
		}
	}
}
