package ecommerce;

import java.util.ArrayList;
import java.util.Arrays;

public class Producto implements Item {
	int SKU;
	String nombre;
	String marca;
	String categoria;
	Double peso;
	Double precio;
	Double precioFinal;// el precio final es porque puede tener un descuento en particular este item,
						// ver ese caso
	ArrayList<AtributoDinamico> AtributosDinamicos = new ArrayList<>();
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
	
	public ArrayList<AtributoDinamico> getAtributosDinamicos() {
	    return AtributosDinamicos;
	}
	
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
	
}
