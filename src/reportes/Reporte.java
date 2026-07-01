package reportes;

public interface Reporte {
	
	public void accept(ReporteVisitor visitor);

}
