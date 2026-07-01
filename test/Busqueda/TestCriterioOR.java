package Busqueda;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import catalogoEItems.Item;

class TestCriterioOR {
	private Criterio siCumple;
	private Criterio noCumple;
	private CriterioOR buscarItemQueCumpleAmbosCriterios;
	private CriterioOR buscarItemQueCumpleUnCriterio;
	private CriterioOR buscarItemQueCumpleNingunCriterio;
	private Item item;
	
	@BeforeEach
	public void setUp() {
		
		
		item=mock(Item.class);
		
		siCumple= mock(Criterio.class);
		when(siCumple.cumple(item)).thenReturn(true);

		noCumple= mock(Criterio.class);
		when(noCumple.cumple(item)).thenReturn(false);
		buscarItemQueCumpleAmbosCriterios = new CriterioOR(siCumple,siCumple);
		buscarItemQueCumpleUnCriterio= new CriterioOR(noCumple,siCumple);
		buscarItemQueCumpleNingunCriterio=new CriterioOR(noCumple,noCumple);
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
		assertTrue(buscarItemQueCumpleUnCriterio.cumple(item));
	}



}
