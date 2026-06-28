package catalogoEItems;

import java.util.ArrayList;
import java.util.List;

import Exceptions.ErrorDeEdicionDePaquete;
import Exceptions.ErrorDeStockInsuficiente;
import ecommerce.Item;


public class Paquete extends Item {
	private List <Item> itemsDelPaquete = new ArrayList<>();
///////////CONSTRUCTOR/////////////
	public Paquete(String nombre, String descripcion, int stock, ArrayList<Item> itemsDelPaquete, double descuento) {
		super();
		this.itemsDelPaquete =itemsDelPaquete;
	}
	///////STOCK//////////
	public void validarQueHayStockDelItem() {
		if(!this.tieneStock()) {
			throw new ErrorDeStockInsuficiente("No hay stock de " +this.getNombre());
		}
	}
	@Override
	public void incrementarStock() {
		this.validarQueExisteStockParaArmarPaquete();
		this.decrementarStockDeCadaItemDePaquete();
		super.incrementarStock();
		
	}
	public void validarQueExisteStockParaArmarPaquete() {
		if(!this.existeStockDeCadaItemEnPaquete()) {
			throw new ErrorDeStockInsuficiente("Error: No hay stock para armar el paquete " + this.getNombre());
		}
	}
	public boolean existeStockDeCadaItemEnPaquete() {
		return itemsDelPaquete.stream().allMatch(item ->item.tieneStock());
		
	}
	public void decrementarStockDeCadaItemDePaquete() {
		itemsDelPaquete.stream().forEach(item-> item.decrementarStock());
	}
	/////PRECIO/////////
	public double precioDePaquete() {
		return itemsDelPaquete.stream().mapToDouble(Item -> Item.precioBaseCalculado).sum();
	}
	public double precioBaseCalculado(){
		return this.precioDePaquete()* (1-this.descuento);
		//aplicar descuento de paquete a la suma de todos los precios
	}
	////////////AGREGAR Y BORRAR ITEMS DE PAQUETE///////
	public void agregarItemAPaquete(Item item) {
		this.verificarQueElItemNoEstaEnElPaquete(item);
		itemsDelPaquete.add(item);
	}
	public void verificarQueElItemNoEstaEnElPaquete(Item item) {
		if(itemEstaEnPaquete(item)) {
			throw new ErrorDeEdicionDePaquete("Error: El item ya esta en el paquete");
		}
	}
	public boolean itemEstaEnPaquete(Item item) {
		return itemsDelPaquete.contains(item);
	}
	public void eliminarItemDePaquete(Item item) {
		validarQueElItemExisteEnElPaquete(item);
		itemsDelPaquete.remove(item);
	}
	public void validarQueElItemExisteEnElPaquete(Item item) {
		if(!itemEstaEnPaquete(item)) {
			throw new ErrorDeEdicionDePaquete("Error: El item no esta en el carrito");
		}
	}
	/////////GETTERS Y SETTERS///////////
	@Override
	public double peso() {
		return this. getItemsDelPaquete().stream().mapToDouble(item->item.peso()).sum();
	}
	public List<Item> getItemsDelPaquete() {
		return itemsDelPaquete;
	}
}
