import java.util.ArrayList;
import java.util.Arrays;

public class Hub {
	int cantidadPuertos;
	Ruta[] Puertos = new Ruta[cantidadPuertos];
	
	public void AgregarDispositivo(Ruta ruta) {
		cantidadPuertos+=1;
		Puertos[cantidadPuertos].dirRed = ruta.dirRed;
		Puertos[cantidadPuertos].interfaz = ruta.interfaz;
	}
	public void RemoverDispositivo() {
		Arrays.copyOf(Puertos, Puertos.length-1);
	}
	
	public void ReenviarPaquete(Paquete p1) {
		for (Ruta ruta : Puertos) {
			
		}
	}
}
