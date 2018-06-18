import java.util.Scanner;

import javax.swing.plaf.synth.SynthSpinnerUI;

public class Windows extends SistemaOperativo {
	
	public void ping(Ip ipd) {
		while(!ipd.esIpValida()) {
			System.out.println("Direccion ip invalida");
			System.out.println("Ingrese nuevamente: ");
			//ipd = teclado.nextline();
		}
		
		Servicio p = new Servicio(ipdispositivo, ipd, 50, "ICMPRequest");
		
		if (ipd.esMismaRed(ipdispositivo)) {
			enviarPaquete(p);
		} else {
			enviarPaquete(nuevoPaqueteRuteo(p));
		}
	}
	
	public Ruteo nuevoPaqueteRuteo(Paquete p) {
		Ruteo pqt = new Ruteo(p);
		return pqt;
	}
}
