package ecommerce;

import java.util.ArrayList;
import java.util.Arrays;

public class Producto implements Item {
	int SKU;
	String Nombre;
	String Marca;
	String Categoria;
	Double Precio;
	Double PrecioFinal;// el precio final es porque puede tener un descuento en particular este item,
						// ver ese caso
	ArrayList<AtributoDinamico> AtributosDinamicos = new ArrayList<>();
	final ArrayList<String> AtributosFijos = new ArrayList<>(
			Arrays.asList("SKU", "Nombre", "Marca", "Categoria", "Precio", "PrecioFinal"));
	// creo un array con todos los nombres de los atributos fijos para lo del
	// validador, lo podria haber puesto como una variable temporal, pero nunca va a
	// cambiar. Asi que lo puse como atributo, bancan chat (?

	public void agregarAtributoDinamico(AtributoDinamico atributoNuevo) {
		if (this.nuevoAtributoNoExisteYa(atributoNuevo)) {
			AtributosDinamicos.add(atributoNuevo);
		}
	}

	public boolean nuevoAtributoNoExisteYa(AtributoDinamico nuevoAtributo) {
		return (!AtributosFijos.contains(nuevoAtributo.getNombre()));
	}

	public void validarProducto(int SKU) {

	}
	public String descripcion() {
		return "No se que poner aca, consultar";
	}
	public double precioBaseCalculado() {
		return this.getPrecioFinal();
	}
	public double getPrecioFinal() {
		return PrecioFinal;
	}
	public void setPrecioFinal(double nuevoPrecioFinal) {
		PrecioFinal=nuevoPrecioFinal;
	}
	public String nombre() {
		return Nombre;
	}
	public void aplicarDescuento(double descuentoDeProducto) {
		//esto es lo del descuento individual, nada que ver con el composite
		setPrecioFinal(this.getPrecioFinal()*(1-descuentoDeProducto));
		
	}
	
}
