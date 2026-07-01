package catalogoEItems;

import java.util.HashMap;
import java.util.Map;

import Exceptions.ErrorDeStringVacio;
import gestionDePedido.Sucursal;

public abstract class Item {
		private String nombre;
		private String descripcion;
		private double precioInicial;
		private Map<Sucursal, Integer> depósito;
		private double descuento;
		private String categoria;
		private double peso;
		private Sucursal sucursal;
		private static int contadorSKU = 0; //static para que la variable sea homogenea en todos los items
		private final int SKU; //final para que no se pueda modificar, sino podria fallar con el diseño de SKU que elegimos
		
		////////////VALIDACIONES DE CONSTRUCTOR////////////////
		protected Item(String nombre, String descripcion,double precio, double descuento, String categoria, double peso, Sucursal sucursal) {
			super();
			this.nombre= nombre;
			this.descripcion=descripcion;
			this.precioInicial=precio;
			this.descuento=descuento;
			this.categoria=categoria;
			this.peso=peso;
			this.sucursal=sucursal;
			this.depósito = new HashMap<>(Map.of(this.sucursal, 0));
			contadorSKU=contadorSKU+1;
			this.SKU= contadorSKU;
			AppEcommerce.getInstancia().agregarItem(this); //cada vez que se cree un Item, va al la lista llamada "catalogo" de el objeto appEcommerce
		}
		public void validarQueNoHayStringsVacios() {
			if (nombre.isBlank() || descripcion.isBlank()|| categoria.isBlank()) {
				throw new ErrorDeStringVacio("Hay parametros de tipo strings vacios");
			}
		}
		///////////////STOCK////////////////////
		public abstract boolean tieneStock();
		
		public abstract void decrementarStock();
		public abstract void incrementarStock();
		public abstract int getStockEnSucursal();
		
		public void validarQueHayStockDelItem() {
			
		}
		//////////PRECIO/////////
		public double precioBaseCalculado() {
			return this.getPrecioInicial()* (1-this.getDescuento());
		}
		///////////////////////////GETTERS Y SETTERS/////////////////////////////
		public String getNombre() {
			return nombre;
		}
		public void setNombre(String nombre) {
			this.nombre = nombre;
		}
		public String getDescripcion() {
			return descripcion;
		}
		public void setDescripcion(String descripcion) {
			this.descripcion = descripcion;
		}
		public double getPrecioInicial() {
			return precioInicial;
		}
		public void setPrecioInicial(double precioInicial) {
			this.precioInicial = precioInicial;
		}
		public double getPeso() {
			return peso;
		}
		public void setPeso(double peso) {
			this.peso = peso;
		}
		public abstract int getStock();
		
		public String getCategoria() {
			return categoria;
		}
		public int getSKU() {
			return SKU;
		}
		public void setStock(Map<Sucursal, Integer> nuevoStock) {
			
		}

		public double getDescuento() {
			return descuento;
		}
		public void setDescuento(double descuento) {
			this.descuento = descuento;
		}
		
		public Map<Sucursal, Integer> getDepósito() {
			return depósito;
		}
		public void setDepósito(Map<Sucursal, Integer> depósito) {
			this.depósito = depósito;
		}
		public void setCategoria(String categoria) {
			this.categoria = categoria;
		}
		public Sucursal getSucursal() {
			return sucursal;
		}
		public void setSucursal(Sucursal sucursal) {
			this.sucursal = sucursal;
		}
}
