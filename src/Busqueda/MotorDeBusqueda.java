package Busqueda;

import java.util.List;

import ecommerce.Catalogo;
import ecommerce.Item;

public class MotorDeBusqueda {
	public Criterio criterio;
	public Catalogo catalogo;
	
	public List<Item> buscar(Criterio criterio){
		return catalogo.getLista().stream().filter(item ->criterio.cumple(item)).toList();	
	}
	
}
