package ecommerce;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Sucursal {
	
	Map<Item, Integer> stockMap;
	String nombreSucursal;
	
	public Sucursal(String nombre, ArrayList<Item> stock) { //posible implementacion
		this.nombreSucursal = nombre;
		this.stockMap = new HashMap<>();
		
		for (Item item : stock) {
	        stockMap.put(item, stockMap.getOrDefault(item, 0) + 1);
	    }
	}

	public boolean hayStock(ArrayList<Item> productos) {
		return false; //implementar
	}

}
