package Busqueda;

import ecommerce.Item;

public class CriterioNombre implements Criterio{
	private String nombre;
	public boolean cumple(Item item) {
		return item.getNombre() == this.getNombre();
	}
	public String getNombre(){
		return this.nombre;
	}
}
