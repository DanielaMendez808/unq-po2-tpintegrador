package reportes;

import catalogoEItems.Item;

public class ItemVendido {
	
	private String nombre;
	private int cantUnidades;
	private double recaudación;
	
	public ItemVendido(Item item) {
		this.nombre = item.getNombre();
		this.cantUnidades = 1;
		this.recaudación = item.precioBaseCalculado();
	}
	
	public void acumularVenta(Item item) {
        this.cantUnidades++;
        this.recaudación += item.precioBaseCalculado();
    }

	public String getNombre() {
		return nombre;
	}

	public int getCantUnidades() {
		return cantUnidades;
	}

	public double getRecaudación() {
		return recaudación;
		
	}
	
	public double getPrecioPromedio() {
		return this.recaudación / this.cantUnidades;
    }

}
