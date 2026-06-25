package ecommerce;

import java.util.ArrayList;

public class Pedido {
	private MetodoDeEnvio metodoDeEnvio;
	EstadoDePedido estadoActual;
	ArrayList <Item> carrito = new ArrayList <>();
	Direccion direccionDeEntrega;
	
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
	
	public float costoEnvio() {
        return metodoDeEnvio.calcularCosto(this);
    }
	
	public int diasEstimados() {
        return metodoDeEnvio.diasEstimados(this);
    }
	
	public void setMetodoDeEnvio(MetodoDeEnvio metodo) {
        this.metodoDeEnvio = metodo;
    }
	
	public float getPesoTotal() {
		return 0;//hacer
	}
	
	public Direccion getDireccionDeEntrega() {
		return direccionDeEntrega;
	}
	
	public ArrayList<Item> getCarrito() {
		return carrito;
	}
	
}


