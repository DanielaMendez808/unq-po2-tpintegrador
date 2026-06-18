package ecommerce;

import java.util.ArrayList;

public class Paquete implements Item{
	String nombre;
	ArrayList <Item> items = new ArrayList <>();
	double descuento;
	public void agregarItemAPaquete(Item item) {
		items.add(item);
	}
	public void eliminarItemDePaquete(Item item) {
		items.remove(item);
	}
	public String nombre() {
		return nombre;
	}
	public String descripcion() {
		return "No se que poner aca, consultar";
	}
	public double precioDePaquete() {
		//sumar el precio de todos los productos que lo componen
		double precioTemporal = 0;
		int iterador = 0;
		while (iterador != items.size()) {
			precioTemporal = precioTemporal + items.get(iterador).precioBaseCalculado();
			iterador = iterador + 1;
		}
		return precioTemporal;
	}
	public double precioBaseCalculado(){
		return this.precioDePaquete()* (1-this.descuento);
			//aplicar descuento de paquete a la suma de todos los precios
	}
}
