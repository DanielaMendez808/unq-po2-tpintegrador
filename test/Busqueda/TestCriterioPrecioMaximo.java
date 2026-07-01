package Busqueda;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import catalogoEItems.Item;

class TestCriterioPrecioMaximo {
	private Item itemDePrecio200;
	private Item itemDePrecio100;
	private Item itemDePrecio150;
	private CriterioPrecioMaximo buscarItemsConPrecioMaximo150; 
	
	@BeforeEach
	public void setUp() {
		buscarItemsConPrecioMaximo150 = new CriterioPrecioMaximo(150.00);
		
		itemDePrecio200= mock(Item.class);
		when(itemDePrecio200.precioBaseCalculado()).thenReturn(200.00);

		itemDePrecio100= mock(Item.class);
		when(itemDePrecio100.precioBaseCalculado()).thenReturn(100.00);
		
		itemDePrecio150= mock(Item.class);
		when(itemDePrecio150.precioBaseCalculado()).thenReturn(150.00);
	}
	@Test
	void testEncuentraItemDePrecioMenor() {
		assertTrue(buscarItemsConPrecioMaximo150.cumple(itemDePrecio100));
	
	}
	@Test
	void testNoEncuentraItemDePrecioMayor() {
		assertFalse(buscarItemsConPrecioMaximo150.cumple(itemDePrecio200));
	
	}
	@Test
	void testEncuentraItemDeIgualPrecio() {
		assertTrue(buscarItemsConPrecioMaximo150.cumple(itemDePrecio150));
	}

}
