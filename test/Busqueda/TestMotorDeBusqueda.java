package Busqueda;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import catalogoEItems.Item;

class TestMotorDeBusqueda {
	//(Electrónica AND disponible) OR (Nombre contiene "oferta");
	CriterioAND criterioEsDeCategoriaElectronicaYEstaDisponible;
	CriterioOR EsDeCategoriaElectronicaYEstaDisponibleOEnElNombreTieneOferta;
	CriterioNombre criterioTieneOfertaEnElNombre;
	CriterioCategoria criterioEsDeCategoriaElectronica;
	CriterioDisponibilidad criterioEstaDisponible;
	Item cargador;
	Item teclado;
	
	
	
	@BeforeEach
	void setUp(){
		
	cargador= mock(Item.class);


	criterioTieneOfertaEnElNombre= new CriterioNombre("OFERTA");
	criterioEsDeCategoriaElectronica= new CriterioCategoria("electronica");
	criterioEstaDisponible= new CriterioDisponibilidad();
	
	criterioEsDeCategoriaElectronicaYEstaDisponible= 
			new CriterioAND (criterioEsDeCategoriaElectronica,criterioEstaDisponible);
	EsDeCategoriaElectronicaYEstaDisponibleOEnElNombreTieneOferta= 
			new CriterioOR(criterioEsDeCategoriaElectronicaYEstaDisponible,criterioTieneOfertaEnElNombre);
		
	}
	@Test
	void TestItemCumpleTodosLosRequisitos() {
		when(cargador.getCategoria()).thenReturn("Electronica");
		when(cargador.getStock()).thenReturn(5);
		when(cargador.getNombre()).thenReturn("Cargador en oferta");
		assertTrue(EsDeCategoriaElectronicaYEstaDisponibleOEnElNombreTieneOferta.cumple(cargador));
	}
	@Test
	void TestItemNoEsDeCategoriaElectronica() {
		when(cargador.getCategoria()).thenReturn("Cargadores");
		when(cargador.getStock()).thenReturn(5);
		when(cargador.getNombre()).thenReturn("Cargador en oferta");
		assertTrue(EsDeCategoriaElectronicaYEstaDisponibleOEnElNombreTieneOferta.cumple(cargador));
	}
	@Test
	void TestItemNoTieneOfertaEnElNombrePeroIgualLoEncuentra() {
		when(cargador.getCategoria()).thenReturn("Electronica");
		when(cargador.getStock()).thenReturn(5);
		when(cargador.getNombre()).thenReturn("Cargador");
		assertTrue(EsDeCategoriaElectronicaYEstaDisponibleOEnElNombreTieneOferta.cumple(cargador));
	}
	@Test
	void TestNoEsDeLaCategoriaPedidaYNoEstaEnOferta() {
		when(cargador.getCategoria()).thenReturn("Cargadores");
		when(cargador.getStock()).thenReturn(5);
		when(cargador.getNombre()).thenReturn("Cargador");
		assertFalse(EsDeCategoriaElectronicaYEstaDisponibleOEnElNombreTieneOferta.cumple(cargador));
	}
	@Test
	void TestItemNoCumpleConNingunaCategoria() {
		when(cargador.getCategoria()).thenReturn("Cargadores");
		when(cargador.getStock()).thenReturn(0);
		when(cargador.getNombre()).thenReturn("Cargador");
		assertFalse(EsDeCategoriaElectronicaYEstaDisponibleOEnElNombreTieneOferta.cumple(cargador));
	}

}
