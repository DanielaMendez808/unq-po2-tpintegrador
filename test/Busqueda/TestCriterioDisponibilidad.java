package Busqueda;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Busqueda.CriterioDisponibilidad;
import catalogoEItems.Item;
public class TestCriterioDisponibilidad {

		private Item itemConStock;
		private Item itemSinStock;
		private CriterioDisponibilidad hayDisponible;
		
		@BeforeEach
		public void setUp() {
			hayDisponible= new CriterioDisponibilidad();
			
			itemSinStock = mock(Item.class);
			when(itemSinStock.getStock()).thenReturn(0);
			
			itemConStock = mock(Item.class);
			when(itemConStock.getStock()).thenReturn(5);
			
			
		}
		@Test
		void testCumpleConDisponibilidad() {
			assertTrue(hayDisponible.cumple(itemConStock));
		
		}
		@Test
		void testNoCumpleSinDisponibilidad() {
			assertFalse(hayDisponible.cumple(itemSinStock));
		}
		
	
}
