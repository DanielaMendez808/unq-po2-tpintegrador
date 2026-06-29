package catalogoEItems;

import java.util.HashMap;
import java.util.Map;

import Exceptions.ErrorDeStringVacio;
import ecommerce.ReporteVisitor;
import gestionDePedido.Sucursal;

public abstract class Item {
		private String nombre;
		private String descripcion;
		private Map<Sucursal, Integer> depósito;
		private double descuento;
		////////////VALIDACIONES DE CONSTRUCTOR////////////////
		protected Item(String nombre, String descripcion,double precio, double descuento) {
			super();
			this.nombre= nombre;
			this.descripcion=descripcion;
			this.descuento=descuento;
			this.depósito = new HashMap<>();
		}
		public void validarQueNoHayStringsVacios() {
			if (nombre.isBlank() || descripcion.isBlank()) {
				throw new ErrorDeStringVacio("Hay parametros de tipo strings vacios");
			}
		}
		///////////////STOCK////////////////////
		public abstract boolean tieneStock();
		
		public abstract void decrementarStock(Sucursal sucursal);
		public abstract void decrementarStock();
		public abstract void incrementarStock(Sucursal sucursal);
		public abstract void incrementarStock();
		public abstract int getStockEnSucursal(Sucursal sucursal);
		
		public void validarQueHayStockDelItem() {
			
		}
		//////////PRECIO/////////
		public abstract double precioBaseCalculado();
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
		public abstract int getStock();
		
		public void setStock(Map<Sucursal, Integer> nuevoStock) {
			
		}

		public double getDescuento() {
			return descuento;
		}
		public void setDescuento(double descuento) {
			this.descuento = descuento;
		}

		public abstract void accept(ReporteVisitor visitor);
		
}
