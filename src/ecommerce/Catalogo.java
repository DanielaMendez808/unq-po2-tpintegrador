package ecommerce;

import java.util.ArrayList;
import java.util.List;

public class Catalogo {
	private String nombre;
	private List <Item> itemsDeCatalogo = new ArrayList<>();
	///////////////////////CONSTRUCTOR////////////////////////////////////////////
	public Catalogo(String nombre, ArrayList<Item> itemsDeCatalogo) {
		super();
		this.validarQueNoTengaNombreVacio(nombre);
		this.nombre=nombre;
		this.itemsDeCatalogo=itemsDeCatalogo;
	}
	
	public void validarQueNoTengaNombreVacio(String nombre){
		if(nombre.isBlank()) {
			throw new RuntimeException("El catalogo no puede tener nombre vacio");
		}
	}
////////////////////////////AGREGAR Y BORRAR ITEMS DEL CATALOGO//////////////////////////////////////
	public void agregarItemACatalogo(Item item) {
		this.verificarQueElItemNoEsteEnElCatalogo(item);
		itemsDeCatalogo.add(item);
	}
	public void verificarQueElItemNoEsteEnElCatalogo(Item item) {
		if(this.itemEstaEnElCatalogo(item)) {
			throw new RuntimeException("El item"+item.nombre()+"ya existe en el catalogo");
		}
	}
	public boolean itemEstaEnElCatalogo(Item item) {
		return itemsDeCatalogo.contains(item);
	}
	public void eliminarItemDelCatalogo(Item item) {
		this.verificarQueElItemEsteEnElCatalogo(item);
		itemsDeCatalogo.remove(item);
	}
	public void verificarQueElItemEsteEnElCatalogo(Item item) {
		if(!this.itemEstaEnElCatalogo(item)) {
			throw new RuntimeException("El item"+item.nombre()+"no existe en el catalogo");
		}
	}
	///////////////////////////////GETTERS Y SETTERS/////////////////////////////////////////////////
	public String getNombre(){
		return this.nombre;
	}
}
