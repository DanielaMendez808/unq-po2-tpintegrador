package Busqueda;

import catalogoEItems.Item;

public class CriterioPrecioMaximo {
	private double precioMaximo;
	public boolean cumple(Item item) {
		return item.precioBaseCalculado() <= this.getPrecioMaximo();
	}
	public double getPrecioMaximo(){
		return this.precioMaximo;
	}
	public CriterioPrecioMaximo(double precioMaximo) {
		super();
		this.precioMaximo = precioMaximo;
	}
}
