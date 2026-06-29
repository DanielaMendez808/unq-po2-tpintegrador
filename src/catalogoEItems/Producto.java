package catalogoEItems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import Exceptions.ErrorDeStockInsuficiente;
import Exceptions.ErrorDeStringVacio;
import ecommerce.ReporteVisitor;
import gestionDePedido.Sucursal;

public class Producto extends Item {
	private Map<Sucursal, Integer> depósito;
	private int SKU;
	private String marca;
	private String categoria;
	private double peso;
	private double precioInicial;
	private Set<AtributoDinamico> AtributosDinamicos = new HashSet<>();

	////////////////CONSTRUCTOR///////////////////////////
	/// 
	public Producto(int SKU, String nombre, String marca, String categoria, String descripcion, double peso,
			double precio, double descuento) {
		super(nombre, descripcion, precio, descuento);
		this.validarQueNoHayStringsVacios();
		this.marca = marca;
		this.categoria = categoria;
		this.peso = peso;
		this.precioInicial = precio;
		// poner precio BaseCaculado como atributo?
	}

	@Override
	public void validarQueNoHayStringsVacios() {
		super.validarQueNoHayStringsVacios();
		if (marca.isBlank() || categoria.isBlank()) {
			throw new ErrorDeStringVacio("Hay parametros de tipo strings vacios");
		}
	}

	/////////////////////// ATRIBUTOS DINAMICOS
	public void agregarAtributoDinamico(AtributoDinamico atributoNuevo) {
		this.validarAtributoDinamico(atributoNuevo);
		this.verificarQueElAtributoNoExisteAntesEnElProducto(atributoNuevo);
		AtributosDinamicos.add(atributoNuevo);
	}

	public void validarAtributoDinamico(AtributoDinamico atributoNuevo) {
		if (algunStringEstaVacio(atributoNuevo)) {
			throw new RuntimeException("Error: Un atributo dinamico esta vacio");
		}
	}

	public boolean algunStringEstaVacio(AtributoDinamico atributoNuevo) {
		return (atributoNuevo.getNombre().isBlank() || atributoNuevo.getValor().isBlank());
	}

	public void verificarQueElAtributoNoExisteAntesEnElProducto(AtributoDinamico atributoNuevo) {
		if (this.existeAtributoDinamicoNuevoEnElProducto(atributoNuevo)) {
			throw new RuntimeException(
					"Error: El atributo " + atributoNuevo.getNombre() + "ya existe en el producto" + this.getNombre());
		}
	}

	public boolean existeAtributoDinamicoNuevoEnElProducto(AtributoDinamico nuevoAtributo) {
		ArrayList<String> AtributosFijos = new ArrayList<>(
				Arrays.asList("SKU", "nombre", "marca", "categoria", "precio", "precioFinal", "peso", "descripcion"));
		return (AtributosFijos.contains(nuevoAtributo.getNombre()));
	}

	//////////////////////STOCK//////////////////////////////// 
	@Override
	public boolean tieneStock() {
		return (getStock() > 0);
	}
	
	public int getStock() {
		return depósito.values().stream().mapToInt(Integer::intValue).sum();
	}
	
	public void setStock(Map<Sucursal, Integer> nuevoStock) {
		this.depósito = nuevoStock;
	}
	
	public int getStockEnSucursal(Sucursal sucursal) {
        return depósito.getOrDefault(sucursal, 0);
    }
	
	public void validarQueHayStockDelItem() {
		if (!this.tieneStock()) {
			throw new RuntimeException("No hay stock de " + this.getNombre());
		}
	}
	
	public void decrementarStock(Sucursal sucursal) {
        int stockActual = getStockEnSucursal(sucursal);
        if (stockActual == 0) {
            throw new ErrorDeStockInsuficiente("No hay stock suficiente en la sucursal.");
        }
        depósito.put(sucursal, stockActual - 1);
    }
	
	@Override
	public void decrementarStock() {
		Sucursal sucursalConStock = depósito.entrySet().stream() //para usar streams
		        .filter(entrada -> entrada.getValue() > 0) //filtro > 0
		        .map(Map.Entry::getKey) //tomo sucursal
		        .findFirst() //busco el primero
		        .orElseThrow(() -> new ErrorDeStockInsuficiente(
		            "No hay stock disponible en ningún depósito para el producto"));
		        depósito.put(sucursalConStock, depósito.get(sucursalConStock) - 1); //descuenta si encuentra
		}
	
	public void incrementarStock(Sucursal sucursal) {
        int stockActual = getStockEnSucursal(sucursal);
        depósito.put(sucursal, stockActual + 1);
    }
	
	@Override
	public void incrementarStock() {
	    Sucursal primeraSucursal = depósito.keySet().stream()
	        .findFirst() //primera sucursal
	        .orElseThrow(() -> new RuntimeException(
	            "No tiene sucursal asignada."
	        ));
	    int stockActual = depósito.getOrDefault(primeraSucursal, 0);
	    depósito.put(primeraSucursal, stockActual + 1);
	}
	
	//////PRECIO////////
	public double precioBaseCalculado() {
		return this.getPrecioInicial() * (1 - this.getDescuento());
	}

	/////////////////////CONSTRUCTORES/////////////////////////////
	public int getSKU() {
		return SKU;
	}

	public void setSKU(int sKU) {
		SKU = sKU;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public double getPeso() {
		return peso;
	}

	public void setPeso(double peso) {
		this.peso = peso;
	}

	public double getPrecioInicial() {
		return precioInicial;
	}

	public void setPrecioInicial(double precioInicial) {
		this.precioInicial = precioInicial;
	}

	public Set<AtributoDinamico> getAtributosDinamicos() {
		return AtributosDinamicos;
	}
	
	@Override
	public void accept(ReporteVisitor visitor) {
		visitor.visitProducto(this);
	}

}
