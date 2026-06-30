package reportes;

public interface ReporteVisitor {
	
	public void visit();
	
}

//clase IElement public abstract void accept(ReporteVisitor visitor);

//ConcElement public void accept(ReporteVisitor visitor) {
//	visitor.visit(this);
//}