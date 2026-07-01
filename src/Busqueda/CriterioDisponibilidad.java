package Busqueda;

public class CriterioDisponibilidad {
	public boolean cumple(Item item) {
		return item.getStock()>0;
	}
}
