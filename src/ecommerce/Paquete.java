package ecommerce;

import java.util.ArrayList;

public class Paquete implements Item{
	String Nombre;
	ArrayList <Producto> productos = new ArrayList <>();
	double Descuento;
	double PrecioFinal;
	public void agregarProductoAPaquete() {
		
	}
	public void eliminarProductoDePaquete() {
		
	}
	public String nombre() {
		return Nombre;
	}
	public String descripcion() {
		return "No se que poner aca, consultar";
	}
	public double precioBaseCalculado() {
		//sumar el precio de todos los productos que lo componen
	}
	public double precioFinalCalculado() {
		//aplicar descuento de paquete a la suma de todos los precios
	}
}
