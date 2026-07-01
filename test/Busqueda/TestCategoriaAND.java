package Busqueda;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import catalogoEItems.Item;

class TestCategoriaAND {
	private Criterio siCumple;
	private Criterio noCumple;
	private CriterioAND buscarItemQueCumpleAmbosCriterios;
	private CriterioAND buscarItemQueCumpleUnCriterio;
	private CriterioAND buscarItemQueCumpleNingunCriterio;
	private Item item;
	
	@BeforeEach
	public void setUp() {
		
		
		item=mock(Item.class);
		
		siCumple= mock(Criterio.class);
		when(siCumple.cumple(item)).thenReturn(true);

		noCumple= mock(Criterio.class);
		when(noCumple.cumple(item)).thenReturn(false);
		buscarItemQueCumpleAmbosCriterios = new CriterioAND(siCumple,siCumple);
		buscarItemQueCumpleUnCriterio= new CriterioAND(noCumple,siCumple);
		buscarItemQueCumpleNingunCriterio=new CriterioAND(noCumple,noCumple);
	}
	@Test
	void testItemCumpleAmbosCriterios() {
		assertTrue(buscarItemQueCumpleAmbosCriterios.cumple(item));
	
	}
	@Test
	void testNoCumpleNingunCriterio() {
		assertFalse(buscarItemQueCumpleNingunCriterio.cumple(item));
	
	}
	@Test
	void testCumpleUnCriterio() {
		assertFalse(buscarItemQueCumpleUnCriterio.cumple(item));
	}


}
