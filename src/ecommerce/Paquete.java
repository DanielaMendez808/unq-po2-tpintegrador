	package ecommerce;

import java.util.ArrayList;
import java.util.List;

public class Paquete extends Item{
	String nombre;
	String descripcion;
	int stock;
	List <Item> itemsDelPaquete = new ArrayList<>();
	double descuento;
	
////////////////////////////CONSTRUCTOR//////////////////////////////////////////
	public Paquete(String nombre, String descripcion, int stock, ArrayList<Item> itemsDelPaquete, double descuento) {
		super();
		this.validarQueNoHayStringsVacios(nombre, descripcion);
		this.nombre=nombre;
		this.descripcion= descripcion;
		this.stock=stock;
		this.itemsDelPaquete =itemsDelPaquete;
		this.descuento=descuento;
		
	}
	
	public void validarQueNoHayStringsVacios(String nombre, String descripcion) {
		if(nombre.isBlank() || descripcion.isBlank()) {
			throw new RuntimeException ("Hay parametros de tipo strings vacios");
		}
	}
////////////////////////////AGREGAR Y BORRAR ITEM A PAQUETE///////////////////////////////////////////////
	public void agregarItemAPaquete(Item item) {
		this.verificarQueElItemNoEstaEnElPaquete(item);
		itemsDelPaquete.add(item);
	}
	public void verificarQueElItemNoEstaEnElPaquete(Item item) {
		if(itemEstaEnPaquete(item)) {
			throw new RuntimeException("Error: El item ya esta en el paquete");
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
			throw new RuntimeException("Error: El item no esta en el carrito");
		}
	}
/////////////////////////////////////STOCK///////////////////////////////////////////////////////////
	public void incrementarStock() {
		this.validarQueExisteStockParaArmarPaquete();
		this.decrementarStockDeCadaItemDePaquete();
		setStock( getStock() + 1);
		
	}
	public void decrementarStockDeCadaItemDePaquete() {
		itemsDelPaquete.stream().forEach(item-> item.decrementarStock());
	}
	public boolean existeStockDeCadaItemEnPaquete() {
		return itemsDelPaquete.stream().allMatch(item ->item.tieneStock());
		
	}
	
	public void validarQueExisteStockParaArmarPaquete() {
		if(!this.existeStockDeCadaItemEnPaquete()) {
			throw new RuntimeException("Error: No hay stock para armar el paquete " + this.nombre());
		}
	}
	public void validarQueHayStockDelItem() { //interfaz
		if(!this.tieneStock()) {
			throw new RuntimeException("No hay stock de " +this.nombre());
		}
	}
	public boolean tieneStock() {
		return (getStock()>0);
	}
	public void decrementarStock() {
		setStock( getStock() + -1);
	}

///////////////////////////////PRECIOS//////////////////////////////////////////////////////////////////////
	public double precioDePaquete() {
		return itemsDelPaquete.stream().mapToDouble(Item -> Item.precioBaseCalculado()).sum();
	}
	public double precioBaseCalculado(){
		return this.precioDePaquete()* (1-this.descuento);
		//aplicar descuento de paquete a la suma de todos los precios
	}
//////////////////////////////////GETTERS Y SETTERS/////////////////////////////////////////////////////////
	public String nombre() {
		return nombre;
	}
	public String descripcion() { // el getter de descripcion
		return this.descripcion;
	}
	public void setDescripcion ( String nuevaDescripcion) {
		this.descripcion=nuevaDescripcion;
	}

	public int getStock() {
		return this.stock;
	}
	public void setStock(int nuevoStock) {
		this.stock=nuevoStock;
	}
	public List<Item> getLista(){
		return itemsDelPaquete;
	}
	public double peso() {
		return this. getLista().stream().mapToDouble(item->item.peso()).sum();
	}
	
	public void accept(ReporteVisitor visitor) {
		visitor.visitPaquete(this);
	}
	
}
