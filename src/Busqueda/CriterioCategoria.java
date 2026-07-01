package Busqueda;

import catalogoEItems.Item;

public class CriterioCategoria implements Criterio{
	private String nombreDeCategoria;
	public boolean cumple(Item item) {
		return item.getCategoria() == this.getNombreDeCategoria();
	}
	public String getNombreDeCategoria(){
		return this.nombreDeCategoria;
	}
}
