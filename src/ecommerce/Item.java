package ecommerce;

public class Item {
	public String nombre;
	public String descripcion;
	public double precioBaseCalculado;
	public int stockEnDeposito;
	public double descuento;
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
	public double getPrecioBaseCalculado() {
		return precioBaseCalculado;
	}
	public void setPrecioBaseCalculado(double precioBaseCalculado) {
		this.precioBaseCalculado = precioBaseCalculado;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public double getDescuento() {
		return descuento;
	}
	public void setDescuento(double descuento) {
		this.descuento = descuento;
	}
	//////////////////////////////////////////////////////////////////////////////////
	public boolean tieneStock();
	public void incrementarStock();
	public void decrementarStock();
	public void validarQueHayStockDelItem();
	public double peso();
	public void accept(ReporteVisitor visitor);
}

