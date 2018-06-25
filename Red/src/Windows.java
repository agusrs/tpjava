import java.util.Scanner;

import javax.swing.plaf.synth.SynthSpinnerUI;


public class Windows extends SistemaOperativo {
	
	public Windows() {
		nombre = "Windows";
		version = 10;
		
	}
	

	
	public void recibirPaquete(Servicio p) throws DestinoInvalidoException {
			if(esDestino(p)) {
				procesarPaquete(p);
			} else {
				throw new DestinoInvalidoException();
			}
		}

	private void procesarPaquete(Servicio p) {
		switch(p.tipo) {
		case WHO:
			Servicio p2 = new Servicio(p.origen, p.destino, 50, p.tipo.SendMessage);
			enviarPaquete(p2);
		case ICMPRequest:
			Servicio p3 = new Servicio(p.origen, p.destino, 50, p.tipo.ICMPResponse);
			enviarPaquete(p3);
		case ICMPResponse:
			System.out.println("Recibido ICMP desde:");
		case SendMessage:
			System.out.println(p.mensaje);
		}
		
		
	}
	}

