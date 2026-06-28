package ecommerce;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Sucursal {
	
	List<Item> stockEnSucursal = new ArrayList <>();
	String nombreSucursal;
	
	public Sucursal(String nombre, List<Item> stock) {
		this.nombreSucursal = nombre;
		this.stockEnSucursal = stock;
	}
	
	public boolean hayStock(List<Item> productos) {
		return false; //implementar
	}
	
	public List<Item> agregarStockSiFalta(List<Item> carrito){
		return //implementar .addAll(carrito);
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
