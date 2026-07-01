package reportes;

public class ItemVendido {
	
	private String nombre;
	private int cantUnidades;
	private float precioPromedio;
	
	public ItemVendido(String nombre, int cantUnidades, float precioPromedio) {
		this.nombre = nombre;
		this.cantUnidades = cantUnidades;
		this.precioPromedio = precioPromedio;
	}

	public String getNombre() {
		return nombre;
	}

	public int getCantUnidades() {
		return cantUnidades;
	}

	public float getPrecioPromedio() {
		return precioPromedio;
	}

}
