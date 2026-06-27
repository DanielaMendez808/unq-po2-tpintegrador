package ecommerce;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Sucursal {
	List<Item> stockEnSucursal= new ArrayList <>();
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
	public void transferirStockASucursal() {
		agregarStockASucursal
	}
	public List<Item> agregarStockASucursal(List <Item> carrito){
		return sucursal.getStockEnSucursal().addAll(carrito);
	}

	public List<Item> getStockEnSucursal() {
		return stockEnSucursal;
	}

	public void setStockEnSucursal(List<Item> stockEnSucursal) {
		this.stockEnSucursal = stockEnSucursal;
	}

	public String getNombreSucursal() {
		return nombreSucursal;
	}

	public void setNombreSucursal(String nombreSucursal) {
		this.nombreSucursal = nombreSucursal;
	}
	

}
