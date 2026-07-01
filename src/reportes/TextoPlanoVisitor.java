package reportes;

import java.util.List;

public class TextoPlanoVisitor implements ReporteVisitor {
	
	private String textoPlano = "";
	
	public void visit(ReporteDeProductosMasVendidos reporte) {
		List<ItemVendido> lista = reporte.ordenarProductosMasVendidos();
		
		String conversión = "Reporte de Productos Más Vendidos \n" +
		                    "Periodo: " + reporte.getDesde() + " / " + reporte.getHasta() + "\n" +
		                    String.format("%-20s | %-10s | %-10s\n", "Item", "Cantidad", "Precio Promedio") +
		                    "---------------------------------------------------\n";
		
		for (ItemVendido iV : lista) {
			conversión += String.format("%-20s | %-10d | $%-10.2f\n", 
	        		iV.getNombre(), 
	        		iV.getCantUnidades(), 
	        		iV.getPrecioPromedio());
	    }
		this.textoPlano = conversión;
	}

	public String getTextoPlano() {
		return textoPlano;
	}
	
}
