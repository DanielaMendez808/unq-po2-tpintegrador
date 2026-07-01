package Busqueda;

import java.util.List;

import catalogoEItems.AppEcommerce;
import catalogoEItems.Item;

public class MotorDeBusqueda {
	public Criterio criterio;
	
	public List<Item> buscar(Criterio criterio){
		return AppEcommerce.getInstancia().getCatalogo().stream().filter(item ->criterio.cumple(item)).toList();	
	}
	
}
