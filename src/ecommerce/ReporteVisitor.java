package ecommerce;

public interface ReporteVisitor {
	
	public void visitProducto(Producto producto);
	public void visitPaquete(Paquete paquete);
	
}
