package catalogoEItems;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Exceptions.ErrorDeAtributoDinamico;
import Exceptions.ErrorDeStringVacio;
import gestionDePedido.Sucursal;

class TestProducto {
	private Producto cargador;
	private Sucursal Ezpeleta;
	private AtributoDinamico voltaje;
	private AtributoDinamico peso;
	private AtributoDinamico atributoDinamicoConStringVacio;
	
	
	@BeforeEach
	public void setUp() {
	peso= new AtributoDinamico("peso","30");
	voltaje= new AtributoDinamico("voltaje","10");
	atributoDinamicoConStringVacio= new AtributoDinamico("","20");
	Ezpeleta= new Sucursal("Sucursal Ezpeleta");
	cargador= new Producto("Cargador tipo C",
			"Motorola",
			"Electronica",
			"de carga Rapida",
			1,
			100.00,
			10.00,
			Ezpeleta);
	}
	@Test
	void crearProductoSinStringsVacios() {
		assertDoesNotThrow(() -> {
		
	        new Producto(
	            "Cargador tipo C",
	            "Motorola",
	            "Electronica",
	            "de carga Rapida",
	            1,
	            100.00,
	            10.00,
	            Ezpeleta
	        );
	    });
		
	}
	@Test
	void testDeberiaLanzarExcepcionSiLaMarcaEstaVacia() {
	    assertThrows(
	    ErrorDeStringVacio.class, () -> {
	        
	        new Producto(
	            "Cargador tipo C",
	            "",                 
	            "Electronica",
	            "de cargaRapida",
	            1,
	            100.00,
	            10.00,
	            Ezpeleta
	        );
	        
	    });
	}
	@Test
	void testAgregarAtributoDinamicoValido() {
		assertDoesNotThrow(() -> {
			cargador.agregarAtributoDinamico(voltaje);
	    });
		
	}
	@Test
	void testAgregarAtributoDinamicoNoValido() {
		assertThrows(
			    ErrorDeAtributoDinamico.class, () -> {
			cargador.agregarAtributoDinamico(atributoDinamicoConStringVacio);
	    });
		
	}
	@Test
	void testFallaAlAgregarAtributoDinamicoQueYaExisteEnLosFijos() {
		cargador.agregarAtributoDinamico(voltaje);
		assertThrows(
			    ErrorDeAtributoDinamico.class, () -> {
			cargador.agregarAtributoDinamico(peso);
	    });
	}
	@Test
	void testFallaAlAgregarAtributoDinamicoQueYaExiste() {
		cargador.agregarAtributoDinamico(voltaje);
		assertThrows(
			    ErrorDeAtributoDinamico.class, () -> {
			cargador.agregarAtributoDinamico(voltaje);
	    });
	}
	
	

}
