package ecommerce;

public interface Item {
	public String nombre();
	public String descripcion();
	public double precioBaseCalculado();
	public boolean tieneStock();
	public void incrementarStock();
	public void decrementarStock();
	public void validarQueHayStockDelItem();
	public double peso();
	public void accept(ReporteVisitor visitor);
}
