package catalogoEItems;

import java.util.ArrayList;
import java.util.List;

import Exceptions.ErrorDeCatalogo;
import Exceptions.ErrorDeStringVacio;

public class Catalogo { //Toda entidad del catálogo debe exponer nombre, descripción y precio base calculado. 
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
			throw new ErrorDeStringVacio("El catalogo no puede tener nombre vacio");
		}
	}
////////////////////////////AGREGAR Y BORRAR ITEMS DEL CATALOGO//////////////////////////////////////
	public void agregarItemACatalogo(Item item) {
		this.verificarQueElItemNoEsteEnElCatalogo(item);
		itemsDeCatalogo.add(item);
	}
	public void verificarQueElItemNoEsteEnElCatalogo(Item item) {
		if(this.itemEstaEnElCatalogo(item)) {
			throw new ErrorDeCatalogo("El item"+item.getNombre()+"ya existe en el catalogo");
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
			throw new ErrorDeCatalogo("El item"+item.getNombre()+"no existe en el catalogo");
		}
	}
	///////////////////////////////GETTERS Y SETTERS/////////////////////////////////////////////////
	public String getNombre(){
		return this.nombre;
	}
	public List<Item> getLista(){
		return this.itemsDeCatalogo;
	}
}
