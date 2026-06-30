package gestionDePedido;

import java.util.ArrayList;
import java.util.List;

import pagos.Comprobante;

public class Usuario {
	
	int DNI;
	String nombre;
	String cbu;
	String billeteraVirtual;
	String tarjeta;
	String email;
	List<Comprobante> comprobantes = new ArrayList<> ();
	
	public void agregarComprobante(Comprobante comprobante) {
		comprobantes.add(comprobante);
	}

	public int getDNI() {
		return DNI;
	}

	public String getNombre() {
		return nombre;
	}

	public String getTarjeta() {
		return tarjeta;
	}

	public List<Comprobante> getComprobantes() {
		return comprobantes;
	}

	public String getCbu() {
		return cbu;
	}

	public String getBilleteraVirtual() {
		return billeteraVirtual;
	}

	public String getEmail() {
		return email;
	}
	
}
