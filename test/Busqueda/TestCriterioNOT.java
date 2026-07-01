package Busqueda;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import catalogoEItems.Item;

class TestCriterioNOT {
	private Criterio siCumple;
	private Criterio noCumple;
	private CriterioNOT buscarItemQueCumpleElCriterio;
	private CriterioNOT buscarItemQueNoCumpleElCriterio;
	private Item item;
	
	@BeforeEach
	public void setUp() {
		
		
		item=mock(Item.class);
		
		siCumple= mock(Criterio.class);
		when(siCumple.cumple(item)).thenReturn(true);

		noCumple= mock(Criterio.class);
		when(noCumple.cumple(item)).thenReturn(false);
		
		buscarItemQueNoCumpleElCriterio = new CriterioNOT(noCumple);
		buscarItemQueCumpleElCriterio = new CriterioNOT(siCumple);

	}
	@Test
	void testItemCumpleElCriterio() {
		assertFalse(buscarItemQueCumpleElCriterio.cumple(item));
	
	}
	@Test
	void testItemNoCumpleElCriterio() {
		assertTrue(buscarItemQueNoCumpleElCriterio.cumple(item));
	}


}
