package Busqueda;

import catalogoEItems.Item;

public class CriterioNombre implements Criterio {
	private String nombre;

	public boolean cumple(Item item) {
		return 
			item.getNombre().replaceAll("\\s+", "").toLowerCase().contains(this.getNombre().replaceAll("\\s+", "").toLowerCase());
	}

	public String getNombre() {
		return this.nombre;
	}

	public CriterioNombre(String nombre) {
		super();
		this.nombre = nombre;
	}

}
