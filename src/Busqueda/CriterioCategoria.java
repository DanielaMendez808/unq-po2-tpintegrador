package Busqueda;

import catalogoEItems.Item;

public class CriterioCategoria implements Criterio{
	private String nombreDeCategoria;
	public boolean cumple(Item item) {
		return item.getCategoria().replaceAll("\\s+", "").toLowerCase().contains(this.getNombreDeCategoria().replaceAll("\\s+", "").toLowerCase());
	}
	public String getNombreDeCategoria(){
		return this.nombreDeCategoria;
	}
	public CriterioCategoria(String nombreDeCategoria) {
		super();
		this.nombreDeCategoria = nombreDeCategoria;
	}
}
