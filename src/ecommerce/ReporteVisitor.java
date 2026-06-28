package ecommerce;

import catalogoEItems.Paquete;
import catalogoEItems.Producto;

public interface ReporteVisitor {
	
	public void visitProducto(Producto producto);
	public void visitPaquete(Paquete paquete);
	
}
