
public class Ip {
	private int p=0;
	private int s=0;
	private int t=0;
	private int c=0;
	
	
	public Ip() {
		p=0;
		s=0;
		t=0;
		c=0;
	}
	public Ip(int pp, int ps, int pt, int pc) {
		if (pp>255 && pp<0 && ps>255 && ps<0 && pt>255 && pt<0 && pc>254 && pc<1) {
			System.out.println("Ip fuera de rango");
		} else {
			p=pp;
			s=ps;
			t=pt;
			c=pc;
		}

	}
	
	public Ip getIp() {
		Ip ip = new Ip(p,s,t,c);
		System.out.println(ip.toString());
		return ip;
	}
	
	public void setIp(int pp, int ps, int pt, int pc) {
		if (pp>255 && pp<0 && ps>255 && ps<0 && pt>255 && pt<0 && pc>254 && pc<1) {
			System.out.println("Ip fuera de rango");
		} else {
			p=pp;
			s=ps;
			t=pt;
			c=pc;
			setDireccion();
		}
	}
	
	public getDireccion() {
		Ip ip 
	}
	
	private void setDireccion() {
		Ip ip = getIp();
		Ip Direccion = new Ip(ip.p,ip.s,ip.t,ip.c);
		Direccion.setIp(Direccion.p, Direccion.s, Direccion.t, 0);
	}
	@Override
	public String toString() {
		return "Ip: " + p + "." + s + "." + t + "." + c;
	}
	
	
}
