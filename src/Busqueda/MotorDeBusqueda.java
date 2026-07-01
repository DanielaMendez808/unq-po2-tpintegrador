package Busqueda;

import java.util.List;

import catalogoEItems.AppEcommerce;
import catalogoEItems.Item;

public class MotorDeBusqueda {
	private final List<Item> catalogo;
	
	public List<Item> buscar(Criterio criterio){
		return AppEcommerce.getInstancia().getCatalogo().stream().filter(item ->criterio.cumple(item)).toList();	
	}
	public MotorDeBusqueda(List<Item> catalogo) { // cuando cree el motor de busqueda le paso la lista de appEcommerce, ahora no porque lo estoy testeando
        this.catalogo = catalogo;
    }
	public List<Item> getCatalogo(){
		return this.catalogo;
	}
	
}
