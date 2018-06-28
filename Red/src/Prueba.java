

public class Prueba {

	public static void main(String[] args) {
		Terminal pc1 = new Terminal();
		Terminal pc2 = new Terminal();
		Terminal pc3 = new Terminal();
		Hub hub1 = new Hub(6);
		Hub hub2 = new Hub();
		Router router1 = new Router();
		pc1.conectar(hub1);
		pc2.conectar(hub1);
		pc3.conectar(hub2);
		hub1.conectar(router1);
		hub2.conectar(router1);
		try {
			pc1.instalarSO("Windows");
			pc2.instalarSO("Windows");
			pc3.instalarSO("Windows");
			router1.instalarSO("CiscoSo");
			Ip ip1 = new Ip(192,168,0,1);
			Ip dgw1 = new Ip(192,168,0,253);
			Ip ip2 = new Ip(192,168,0,2);
			Ip dgw2 = new Ip(192,168,0,254);
			Ip ip3 = new Ip(192,168,0,0);
			Ip ip4 = new Ip(10,10,10,0);
			Ip ip5 = new Ip(10,10,10,1);
			((Windows) pc1.getSO()).agregarIp(ip1);
			((Windows) pc1.getSO()).agregarDgw(dgw1);
			((Windows) pc1.getSO()).agregarIp(ip2);
			((Windows) pc1.getSO()).agregarDgw(dgw2);
			((CiscoSo) router1.getSO()).setIp(ip3, 1);
			((CiscoSo) router1.getSO()).setIp(ip4, 2);
			((Windows) pc1.getSO()).agregarIp(ip5);
			
			
			
			
			System.out.println("Termina bien");

			
		} catch (SistemaOperativoInvalidoException e) {
			System.out.println(e.mensaje);
			e.printStackTrace();
		} catch (DispositivoInvalidoException e) {
			System.out.println(e.mensaje);
		} catch (IpFueraDeRangoException e) {

		} catch (SistemaOperativoFaltanteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		

	}



}
