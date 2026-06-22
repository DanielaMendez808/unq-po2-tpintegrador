package ecommerce;

public interface Item {
	public String nombre();
	public String descripcion();
	public double precioBaseCalculado();
	public boolean tieneStock();
}
