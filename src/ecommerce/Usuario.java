package ecommerce;

import java.util.ArrayList;
import java.util.List;

public class Usuario {
	int DNI;
	String nombre;
	List<NotaDeCredito> notasDeCredito = new ArrayList<> ();
	public void nuevaNotaDeCredito(double monto) {
		notasDeCredito.add(new NotaDeCredito(DNI,monto));
	}
}
