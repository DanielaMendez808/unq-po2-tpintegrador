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
	Direccion dirección;
	Sucursal sucursalPreferida;
	MetodoDeEnvio envioPreferido;
	List<Comprobante> comprobantes = new ArrayList<> ();
	
	public Usuario(int dni, String nombre, String cbu, String billeteraVirtual, String tarjeta,
			       String email, Direccion dirección, Sucursal sucursalPref) {
		this.DNI = dni;
		this.nombre = nombre;
		this.cbu = cbu;
		this.billeteraVirtual = billeteraVirtual;
		this.tarjeta = tarjeta;
		this.email = email;
		this.dirección = dirección;
		this.sucursalPreferida = sucursalPref;
	}
	
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
	
	public Direccion getDirección() {
		return dirección;
	}
	
	public Sucursal getSucursalPreferida() {
		return sucursalPreferida;
	}
	
	public MetodoDeEnvio getEnvioPreferido() {
		return envioPreferido;
	}
}
