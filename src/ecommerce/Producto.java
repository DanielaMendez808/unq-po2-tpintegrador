package ecommerce;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Producto implements Item {
	int stock;
	int SKU;
	String nombre;
	String marca;
	String categoria;
	String descripcion;
	Double peso;
	Double precio;
	Double precioFinal;// el precio final es porque puede tener un descuento en particular este item,
						// ver ese caso
	Set<AtributoDinamico> AtributosDinamicos = new HashSet<>();
	final ArrayList<String> AtributosFijos = new ArrayList<>(
			Arrays.asList("SKU", "nombre", "marca", "categoria", "precio", "precioFinal", "peso", "descripcion"));
	// creo un array con todos los nombres de los atributos fijos para lo del
	// validador, lo podria haber puesto como una variable temporal, pero nunca va a
	// cambiar. Asi que lo puse como atributo, bancan chat (?

	/////////////////////CONSTRUCTOR////////////////////////////////////
	public Producto(int SKU, String nombre, String marca, String categoria, String descripcion, double peso,
			double precio, double precioFinal) {
		super();
		this.validarQueNoHayStringsVacios();
		this.nombre = nombre;
		this.marca = marca;
		this.categoria = categoria;
		this.descripcion = descripcion;
		this.peso = peso;
		this.precio = precio;
		this.precioFinal = precioFinal;

	}

	public void validarQueNoHayStringsVacios() {
		if (nombre.isBlank() || marca.isBlank() || categoria.isBlank() || descripcion.isBlank()) {
			throw new RuntimeException("Hay parametros de tipo strings vacios");
		}
	}

	////////////////ATRIBUTOS DINAMICOS//////////////////////////////////

	public Set<AtributoDinamico> getAtributosDinamicos() { // setter de atributos dinamicos
		return AtributosDinamicos;
	}

	public void agregarAtributoDinamico(AtributoDinamico atributoNuevo) {
		this.validarAtributoDinamico(atributoNuevo);
		this.verificarQueElAtributoNoExisteAntesEnElProducto(atributoNuevo);
		AtributosDinamicos.add(atributoNuevo);
	}

	public void verificarQueElAtributoNoExisteAntesEnElProducto(AtributoDinamico atributoNuevo) {
		if (this.existeAtributoDinamicoNuevoEnElProducto(atributoNuevo)) {
			throw new RuntimeException(
					"Error: El atributo " + atributoNuevo.getNombre() + "ya existe en el producto" + this.nombre());
		}
	}

	public boolean existeAtributoDinamicoNuevoEnElProducto(AtributoDinamico nuevoAtributo) {
		return (AtributosFijos.contains(nuevoAtributo.getNombre()));
	}

	public void validarAtributoDinamico(AtributoDinamico atributoNuevo) {
		if (algunStringEstaVacio(atributoNuevo)) {
			throw new RuntimeException("Error: Un atributo dinamico esta vacio");
		}
	}
	public boolean algunStringEstaVacio(AtributoDinamico atributoNuevo) {
		return (atributoNuevo.getNombre().isBlank() || atributoNuevo.getValor().isBlank());
	}

	/////////////////////STOCK//////////////////////////////
	public boolean tieneStock() {
		return (getStock() > 0);
	}
	
	public void validarQueHayStockDelItem() {
		if (!this.tieneStock()) {
			throw new RuntimeException("No hay stock de " + this.getNombre());
		}
	}
	
	public void decrementarStock() {
		setStock(getStock() - 1);
	}
	public void incrementarStock() {
		setStock(getStock() + 1);
	}
///////////////////////PRECIOS//////////////////////////////////////////////////////
	public void aplicarDescuento(double descuentoDeProducto) {
		// esto es lo del descuento individual, nada que ver con el composite
		setPrecioFinal(this.getPrecioFinal() * (1 - descuentoDeProducto));
		
	}
	public double precioBaseCalculado() {
		return this.getPrecioFinal();
	}
///////////////////////GETTERS Y SETTERS//////////////////////////////////////
	public String getNombre() {
		return nombre;
	}

	public double peso() {
		return peso;
	}

	public String descripcion() { // el getter de descripcion
		return this.descripcion;
	}

	public void setDescripcion(String nuevaDescripcion) {
		this.descripcion = nuevaDescripcion;
	}


	public double getPrecioFinal() {
		return precioFinal;
	}

	public void setPrecioFinal(double nuevoPrecioFinal) {
		precioFinal = nuevoPrecioFinal;
	}

	public String nombre() { // getter de nombre
		return nombre;
	}
	public int getStock() {
		return this.stock;
	}
	
	public void setStock(int nuevoStock) {
		this.stock = nuevoStock;
	}
	
	public void accept(ReporteVisitor visitor) {
		visitor.visitProducto(this);
	}

}
