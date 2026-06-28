package catalogoEItems;

import Exceptions.ErrorDeStringVacio;
import ecommerce.ReporteVisitor;

public abstract class Item {
		private String nombre;
		private String descripcion;
		private int stockEnDeposito;
		private double descuento;
		////////////VALIDACIONES DE CONSTRUCTOR////////////////
		protected Item(String nombre, String descripcion,double precio, int stock, double descuento ) {
			super();
			this.nombre= nombre;
			this.descripcion=descripcion;
			this.stockEnDeposito=stock;
			this.descuento=descuento;
		}
		public void validarQueNoHayStringsVacios() {
			if (nombre.isBlank() || descripcion.isBlank()) {
				throw new ErrorDeStringVacio("Hay parametros de tipo strings vacios");
			}
		}
		///////////////STOCK////////////////////
		public boolean tieneStock() {
			return (getStock() > 0);
		}
		
		public void decrementarStock() {
			setStock(getStock() - 1);
		}
		public void incrementarStock() {
			setStock(getStock() + 1);
		}
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
		public int getStock() {
			return stockEnDeposito;
		}
		public void setStock(int stock) {
			this.stockEnDeposito = stock;
		}
		public double getDescuento() {
			return descuento;
		}
		public void setDescuento(double descuento) {
			this.descuento = descuento;
		}

		public void accept(ReporteVisitor visitor) {
			visitor.visitProducto(this);
		}
}
