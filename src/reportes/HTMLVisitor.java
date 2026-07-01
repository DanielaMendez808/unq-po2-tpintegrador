package reportes;

import java.util.List;

public class HTMLVisitor implements ReporteVisitor {
	
	private String HTML = "";
	
	public void visit(ReporteDeProductosMasVendidos reporte) {
		List<ItemVendido> lista = reporte.ordenarProductosMasVendidos();
		
		String conversión = "<h2>Reporte de Productos Más Vendidos</h2>\n" +
		                    "<p>Periodo: " + reporte.getDesde() + " / " + reporte.getHasta() + "</p>\n" +
				            "<table>\n" +
		                    String.format("%-20s | %-10s | %-10s\n", "Item", "Cantidad", "Precio Promedio") +
		                    "---------------------------------------------------\n";
		
		for (ItemVendido iV : lista) {
			conversión += String.format("%-20s | %-10d | $%-10.2f\n", 
	        		iV.getNombre(), 
	        		iV.getCantUnidades(), 
	        		iV.getPrecioPromedio());
	    }
		conversión += "</table>";
		this.HTML = conversión;
	}

	public String getHTML() {
		return HTML;
	}

}
