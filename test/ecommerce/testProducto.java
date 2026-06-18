package ecommerce;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

class testProducto {
	private Producto cargador;
	private AtributoDinamico voltaje;
	
	@BeforeEach
	public void setUp() {
		cargador = new Producto(123,"cargadorTipoC","Motorola","electronica",1,30000,30000);
		voltaje = new AtributoDinamico("voltaje","5");
		
	}
	@Test
	void agregarUnAtributoDinamicoQueNoExiste() {
		cargador.agregarAtributoDinamico(voltaje);
		ArrayList <AtributoDinamico> soloVoltaje= new ArrayList<>(Arrays.asList(voltaje));
		assertEquals(cargador.getAtributosDinamicos(),soloVoltaje);
		
	}

}
