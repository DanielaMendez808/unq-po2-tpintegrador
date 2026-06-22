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
	Double peso;
	Double precio;
	Double precioFinal;// el precio final es porque puede tener un descuento en particular este item,
						// ver ese caso
	Set <AtributoDinamico> AtributosDinamicos = new HashSet<>();
	final ArrayList<String> AtributosFijos = new ArrayList<>(
			Arrays.asList("SKU", "nombre", "marca", "categoria", "precio", "precioFinal","peso"));
	// creo un array con todos los nombres de los atributos fijos para lo del
	// validador, lo podria haber puesto como una variable temporal, pero nunca va a
	// cambiar. Asi que lo puse como atributo, bancan chat (?
	public Producto(int SKU, String nombre,String marca, String categoria,double peso, double precio, double precioFinal) {
		super();
		this.nombre= nombre;
		this.marca=marca;
		this.categoria=categoria;
		this.peso=peso;
		this.precio=precio;
		this.precioFinal=precioFinal;
		
	}

	public Set<AtributoDinamico> getAtributosDinamicos() {
	    return AtributosDinamicos;
	}
	
	public void agregarAtributoDinamico(AtributoDinamico atributoNuevo) {
		if (this.nuevoAtributoNoExisteYa(atributoNuevo)) {
			AtributosDinamicos.add(atributoNuevo);
		} else { throw new RuntimeException("Error: El atributo " + atributoNuevo.getNombre() + "ya existe");
		}
	}

	public boolean nuevoAtributoNoExisteYa(AtributoDinamico nuevoAtributo) {
		return (!AtributosFijos.contains(nuevoAtributo.getNombre()));
	}
	public String getNombre() {
		return nombre;
	}
	public double getPeso() {
		return peso;
	}
	public void validarProducto() {
		validarSiTieneAtributosFijosVacios();
			//algo, nose si tiene que ser void o boolean
			
	}
	public void validarSiTieneAtributosFijosVacios() {
		if (tieneAtributosFijosVacios()) {
			throw new RuntimeException("Error: Un atributo fijo esta vacio");
		}
	}
	public void validarSiTieneAtributosDinamicosVacios() {
		if(tieneAtributosDinamicosVacios()) {
			throw new RuntimeException("Error: Un atributo dinamico esta vacio");
		}
	}
	
	public boolean tieneAtributosFijosVacios() {
		return nombre.isBlank() && marca.isBlank() && categoria.isBlank(); 
		
	}
	public boolean tieneAtributosDinamicosVacios() {
		for (AtributoDinamico atributo : AtributosDinamicos) { //por cada uno quiero que
			if (atributo.getValor().isBlank()) {
				return true; // encontre algun valor dinamico vacio
			}
		}
		return false; // termine de recorrer todos los valores del set y ninguno tenia valores vacios
	}
	
	public String descripcion() {
		return "No se que poner aca, consultar";
	}
	public double precioBaseCalculado() {
		return this.getPrecioFinal();
	}
	public double getPrecioFinal() {
		return precioFinal;
	}
	public void setPrecioFinal(double nuevoPrecioFinal) {
		precioFinal=nuevoPrecioFinal;
	}
	public String nombre() {
		return nombre;
	}
	public void aplicarDescuento(double descuentoDeProducto) {
		//esto es lo del descuento individual, nada que ver con el composite
		setPrecioFinal(this.getPrecioFinal()*(1-descuentoDeProducto));
		
	}
	public int getStock() {
		return this.stock;
	}
	public boolean tieneStock() {
		return (getStock()>0);
	}
	
}
