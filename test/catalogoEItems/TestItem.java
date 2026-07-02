package catalogoEItems;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import gestionDePedido.Sucursal;

class TestProducto {
	private Producto cargador;
	private Sucursal Ezpeleta;
	
	@BeforeEach
	public void setUp() {
	Ezpeleta= new Sucursal("Sucursal Ezpeleta");
	cargador= new Producto("Cargador tipo C",
			"Motorola",
			"Electronica",
			"de cargaRapida",
			1,
			100.00,
			10.00,
			Ezpeleta);
	
	}
	@Test
	void test() {
		fail("Not yet implemented");
	}

}
