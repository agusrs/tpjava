
public class Ip {
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + c;
		result = prime * result + p;
		result = prime * result + s;
		result = prime * result + t;
		return result;
	}


	private int p=0;
	private int s=0;
	private int t=0;
	private int c=0;
	private String ip;
	private String direccion;
	
	public String getIp() {
		return ip;
	}

	public String getDireccion() {
		return direccion;
	}
	
	public Ip() {
		p=0;
		s=0;
		t=0;
		c=0;
		ip=p+ "."+ s + "." + t + "." + c;
		direccion=p+ "."+ s + "." + t + "." + "0";
	}
	
	public Ip(int pp, int ps, int pt, int pc) throws IpFueraDeRangoException {
		if (pp>255 && pp<0 && ps>255 && ps<0 && pt>255 && pt<0 && pc>254 && pc<1) {
			throw new IpFueraDeRangoException();
		} else {
			p=pp;
			s=ps;
			t=pt;
			c=pc;
			ip=p+ "."+ s + "." + t + "." + c;
			direccion=p+ "."+ s + "." + t + "." + "0";
		}

	}
	
	public void setIp(int pp, int ps, int pt, int pc) throws IpFueraDeRangoException {
		if (pp>255 && pp<0 && ps>255 && ps<0 && pt>255 && pt<0 && pc>254 && pc<1) {
			throw new IpFueraDeRangoException();
		} else {
			p=pp;
			s=ps;
			t=pt;
			c=pc;
			ip=p+ "."+ s + "." + t + "." + c;
			direccion=p+ "."+ s + "." + t + "." + "0";
		}
	}
	
	
	public boolean esMismaRed(Ip ip) {
		boolean resultado = false;
		if (this.p==ip.p) {
			if (this.s==ip.s) {
				if (this.t==ip.t) {
					resultado = true;
				}
			}
		}
		return resultado;
	}
	
	public boolean esIpValida() {
		boolean resultado = true;
		if(p>255 && p<0 && s>255 && s<0 && t>255 && t<0 && c>254 && c<1) {
			resultado = false;
		}
		return resultado;
	}
	
	public boolean equals(Ip ip) {
		if (this == ip)
			return true;
		if (ip == null)
			return false;
		if (getClass() != ip.getClass())
			return false;
		Ip other = (Ip) ip;
		if (c != other.c)
			return false;
		if (p != other.p)
			return false;
		if (s != other.s)
			return false;
		if (t != other.t)
			return false;
		return true;
	}
	

	
}
