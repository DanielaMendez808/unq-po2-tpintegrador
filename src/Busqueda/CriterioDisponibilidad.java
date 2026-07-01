package Busqueda;

import catalogoEItems.Item;

public class CriterioDisponibilidad implements Criterio {
	public boolean cumple(Item item) {
		return item.getStock()>0;
	}

	public CriterioDisponibilidad() {
		super();
	}
	
}
