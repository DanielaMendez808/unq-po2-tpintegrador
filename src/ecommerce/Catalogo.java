package ecommerce;

import java.util.ArrayList;
import java.util.List;

public class Catalogo {
	private List <Item> itemsDeCatalogo = new ArrayList<>();
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
}
