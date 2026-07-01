package reportes;

import java.util.List;

public class CSVVisitor implements ReporteVisitor {
	
	private String CSV = "";
	
	public void visit(ReporteDeProductosMasVendidos reporte) {
		List<ItemVendido> lista = reporte.ordenarProductosMasVendidos();
		
		String conversión = "Item; Cantidad; Precio Promedio\n";
		
		for (ItemVendido iV : lista) {
			conversión += iV.getNombre() + ";" + 
					      iV.getCantUnidades() + ";" + 
					      String.format("%.2f", iV.getPrecioPromedio()) + "\n";
	    }
		this.CSV = conversión;
	}

	public String getCSV() {
		return CSV;
	}
	
}
