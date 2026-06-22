package ecommerce;

public class AtributoDinamico {
	String nombre;
	String valor;

	public String getNombre() {
		return this.nombre;
	}

	public AtributoDinamico(String nombre, String valor) {
		super();
		this.nombre = nombre;
		this.valor = valor;
	}

	// preguntar si dos atributos dinamicos son iguales
	public boolean equals(Object atributoNuevo) {
		if (this == atributoNuevo)
			return true;

		// esta bien preguntarle a un objeto su clase? no suena
		if (atributoNuevo == null || getClass() != atributoNuevo.getClass())
			return false;

		//no entiendo que hace este paso... revisar
		AtributoDinamico that = (AtributoDinamico) atributoNuevo;

		//pregunto si tienen el mismo nombre
		return nombre.equals(that.nombre);
	}

	public int hashCode() {
		return nombre.hashCode();
	}
}
