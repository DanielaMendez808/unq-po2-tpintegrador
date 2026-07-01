package Busqueda;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import catalogoEItems.Item;
import Busqueda.CriterioCategoria;

class TestCriterioCategoria {
	private Item cargadorTipoA;
	private Item remeraSeleccion;
	private CriterioCategoria buscarItemsDeCategoriaIndumentaria; 
	private CriterioCategoria buscarItemsDeCategoriaMalEscrita;
	
	@BeforeEach
	public void setUp() {
		buscarItemsDeCategoriaIndumentaria = new CriterioCategoria("indumentaria");
		buscarItemsDeCategoriaMalEscrita = new CriterioCategoria("inudmentaria");
		
		cargadorTipoA = mock(Item.class);
		when(cargadorTipoA.getCategoria()).thenReturn("Electronica");
		
		remeraSeleccion = mock(Item.class);
		when(remeraSeleccion.getCategoria()).thenReturn("Indumentaria");
		
	}
	@Test
	void testEncuentraItemDeLaCategoria() {
		assertTrue(buscarItemsDeCategoriaIndumentaria.cumple(remeraSeleccion));
	
	}
	@Test
	void testNoEncuentraItemDeLaCategoria() {
		assertFalse(buscarItemsDeCategoriaIndumentaria.cumple(cargadorTipoA));
	
	}
	@Test
	void testNoEncuentraConLaCategoriaMalEscrita() {
		assertFalse(buscarItemsDeCategoriaMalEscrita.cumple(remeraSeleccion));
	}
	
}
