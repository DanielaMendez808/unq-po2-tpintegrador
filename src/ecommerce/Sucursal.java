package ecommerce;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Sucursal {
	
	Map<Item, Integer> stockMap;
	String nombreSucursal;
	
	public Sucursal(String nombre, List<Item> stock) { //posible implementacion
		this.nombreSucursal = nombre;
		this.stockMap = new HashMap<>();
		
		for (Item item : stock) {
	        stockMap.put(item, stockMap.getOrDefault(item, 0) + 1);
	    }
	}

	public boolean hayStock(List<Item> productos) {
		return false; //implementar
	}

}
