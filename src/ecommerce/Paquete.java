package ecommerce;

import java.util.ArrayList;

public class Paquete implements Item{
	String nombre;
	ArrayList <Item> itemsDelPaquete = new ArrayList <>();
	double descuento;
	public void agregarItemAPaquete(Item item) {
		validarQueElItemNoExisteYa(item);
		validarQueHayStockDelItem(item);
		itemsDelPaquete.add(item);
	}
	public void validarQueElItemNoExisteYa(Item item) {
		if(itemEstaEnCarrito(item)) {
			throw new RuntimeException("Error: El item ya esta en el carrito");
		}
	}
	public boolean itemEstaEnCarrito(Item item) {
		return itemsDelPaquete.contains(item);
	}
	public void validarQueHayStockDelItem(Item item){
		if(!item.tieneStock()) {
			throw new RuntimeException("Error: No hay stock de " + item.nombre());
		}
	public boolean tieneStock() {
		boolean porAhoraHayStock = false;
		int iterador = 0;
		while (iterador !=  itemsDelPaquete.size()) {
			porAhoraHayStock = porAhoraHayStock && itemsDelPaquete.get(iterador).tieneStock();
			iterador = iterador + 1;
		}
		return porAhoraHayStock;
		
	}
	public void eliminarItemDePaquete(Item item) {
		validarQueElItemExisteEnElPaquete(item);
		itemsDelPaquete.remove(item);
	}
	public void validarQueElItemExisteEnElPaquete(Item item) {
		if(!itemEstaEnCarrito(item)) {
			throw new RuntimeException("Error: El item no esta en el carrito");
		}
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
		while (iterador !=  itemsDelPaquete.size()) {
			precioTemporal = precioTemporal + itemsDelPaquete.get(iterador).precioBaseCalculado();
			iterador = iterador + 1;
		}
		return precioTemporal;
	}
	public double precioBaseCalculado(){
		return this.precioDePaquete()* (1-this.descuento);
			//aplicar descuento de paquete a la suma de todos los precios
	}
}
