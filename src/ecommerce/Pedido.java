package ecommerce;

import java.util.ArrayList;

public class Pedido {
	ArrayList <Item> items = new ArrayList <>();
	public double precioAPagar() {
		double precioTemporal = 0;
		int iterador = 0;
		while (iterador != items.size()) {
			precioTemporal = precioTemporal + items.get(iterador).precioBaseCalculado();
			iterador = iterador + 1;
		}
		return precioTemporal;
		
	}
}

