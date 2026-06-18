package ecommerce;

import java.util.ArrayList;

public class Pedido {
	EstadoDePedido estadoActual;
	ArrayList <Item> carrito = new ArrayList <>();
	public double precioAPagar() {
		double precioTemporal = 0;
		int iterador = 0;
		while (iterador != carrito.size()) {
			precioTemporal = precioTemporal + carrito.get(iterador).precioBaseCalculado();
			iterador = iterador + 1;
		}
		return precioTemporal;
		
	}
	public void setEstadoDePedido(EstadoDePedido nuevoEstado) {
		this.estadoActual= nuevoEstado;
	}
}


