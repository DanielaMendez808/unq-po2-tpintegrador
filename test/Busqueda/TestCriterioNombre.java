package Busqueda;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Busqueda.CriterioNombre;
import catalogoEItems.Item;

class TestCriterioNombre {
	private Item cargadorTipoA;
	private Item cargadorTipoC;
	private Item teclado;
	private CriterioNombre buscarCargadorTipoC;
	private CriterioNombre buscarCargador;
	
	@BeforeEach
	public void setUp() {
		buscarCargadorTipoC = new CriterioNombre("Cargador Tipo c");
		buscarCargador= new CriterioNombre("cargador");
		
		cargadorTipoA = mock(Item.class);
		when(cargadorTipoA.getNombre()).thenReturn("Cargador Tipo A");
		
		cargadorTipoC = mock(Item.class);
		when(cargadorTipoC.getNombre()).thenReturn("CargadorTipo C");
		
		teclado = mock(Item.class);
		when(teclado.getNombre()).thenReturn("Teclado");
		
		
	}
	@Test
	void testCumpleConNombreValido() {
		assertTrue(buscarCargadorTipoC.cumple(cargadorTipoC));
	
	}
	@Test
	void testNoCumpleConNombreValido() {
		assertFalse(buscarCargadorTipoC.cumple(cargadorTipoA));
	
	}
	@Test
	void testCumpleConNombreContenidoYEnMinuscula() {
		assertTrue(buscarCargador.cumple(cargadorTipoC));
	}
	
}