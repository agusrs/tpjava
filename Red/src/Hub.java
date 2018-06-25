import java.util.ArrayList;
import java.util.Arrays;

public class Hub extends Dispositivo {
	
	public Hub(int cantp) {
		so=null;
		paqueteactual=null;
		puertos = cantp;
		dispositivosConectados=0;
	}
	
	public void ReenviarPaquete(Paquete p1) {
		for (Dispositivo x : interfaces) {
			try {
				x.getSO().recibirPaquete(p1);
			} catch (SistemaOperativoFaltanteException e) {
				System.out.println("El dispositivo: " + x + "no tiene un sistema operativo instalado");
			} catch (PackageTypeException e) {
				
			}
		}
	}
	
	public void conectar(Dispositivo d) {
		interfaces[dispositivosConectados] = d;
		dispositivosConectados++;
	}
}
