package catalogoEItems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import Exceptions.ErrorDeAtributoDinamico;
import Exceptions.ErrorDeStockInsuficiente;
import Exceptions.ErrorDeStringVacio;
import gestionDePedido.Sucursal;

public class Producto extends Item {
	private String marca;
	private Set<AtributoDinamico> AtributosDinamicos = new HashSet<>();

	////////////////CONSTRUCTOR///////////////////////////
	/// 
	public Producto(String nombre, String marca, String categoria, String descripcion, double peso,
			double precio, double descuento,Sucursal sucursal) {
		super(nombre, descripcion, precio, descuento,categoria,peso,sucursal);
		this.marca = marca;
		this.validarQueNoHayStringsVacios();
	}

	@Override
	public void validarQueNoHayStringsVacios() {
		super.validarQueNoHayStringsVacios();
		if (marca.isBlank() ) {
			throw new ErrorDeStringVacio("Hay parametros de tipo strings vacios");
		}
	}

	/////////////////////// ATRIBUTOS DINAMICOS //////////////
	public void agregarAtributoDinamico(AtributoDinamico atributoNuevo) {
		this.validarAtributoDinamico(atributoNuevo);
		this.verificarQueElAtributoNoExisteAntesEnElProducto(atributoNuevo);
		AtributosDinamicos.add(atributoNuevo);
	}

	public void validarAtributoDinamico(AtributoDinamico atributoNuevo) {
		if (algunStringEstaVacio(atributoNuevo)) {
			throw new ErrorDeAtributoDinamico("Error: Un atributo dinamico esta vacio");
		}
	}

	public boolean algunStringEstaVacio(AtributoDinamico atributoNuevo) {
		return (atributoNuevo.getNombre().isBlank() || atributoNuevo.getValor().isBlank());
	}

	public void verificarQueElAtributoNoExisteAntesEnElProducto(AtributoDinamico atributoNuevo) {
		if (this.existeAtributoDinamicoNuevoEnElProducto(atributoNuevo)) {
			throw new ErrorDeAtributoDinamico(
					"Error: El atributo " + atributoNuevo.getNombre() + "ya existe en el producto" + this.getNombre());
		}
	}

	public boolean existeAtributoDinamicoNuevoEnElProducto(AtributoDinamico nuevoAtributo) {
		ArrayList<String> AtributosFijos = new ArrayList<>(
				Arrays.asList("SKU", "nombre", "marca", "categoria", "precio", "precioFinal", "peso", "descripcion"));
		return (AtributosFijos.contains(nuevoAtributo.getNombre())) || this.getAtributosDinamicos().contains(nuevoAtributo);
	}

	//////////////////////STOCK//////////////////////////////// 
	@Override
	public boolean tieneStock() {
		return (getStock() > 0);
	}
	
	public int getStock() {
		return this.getDepósito().values().stream().mapToInt(Integer::intValue).sum();
	}
	
	public void setStock(Map<Sucursal, Integer> nuevoStock) {
		this.setDepósito(nuevoStock);
	}
	
	public int getStockEnSucursal() {
        return this.getDepósito().getOrDefault(getSucursal(), 0);
    }
	
	public void validarQueHayStockDelItem() {
		if (!this.tieneStock()) {
			throw new RuntimeException("No hay stock de " + this.getNombre());
		}
	}
	
	public void decrementarStock() {
        if (getStockEnSucursal() == 0) {
            throw new ErrorDeStockInsuficiente("No hay stock suficiente en la sucursal.");
        }
        this.getDepósito().merge(this.getSucursal(), -1, Integer::sum);
    }
	
	@Override
	public void incrementarStock() {
        this.getDepósito().merge(this.getSucursal(), 1, Integer::sum);
    }
	
	//////PRECIO////////

	/////////////////////CONSTRUCTORES/////////////////////////////

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public Set<AtributoDinamico> getAtributosDinamicos() {
		return AtributosDinamicos;
	}

}
