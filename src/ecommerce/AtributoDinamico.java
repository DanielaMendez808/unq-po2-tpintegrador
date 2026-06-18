package ecommerce;

public class AtributoDinamico {
	String nombre;
	String valor;
	public String getNombre() {
		return this.nombre;
	}
	public AtributoDinamico(String nombre, String valor) {
		super();
		this.nombre=nombre;
		this.valor=valor;
	}
}
