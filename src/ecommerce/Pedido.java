package ecommerce;

import java.util.ArrayList;
import java.util.List;

public class Pedido {
	private MetodoDeEnvio metodoDeEnvio;
	EstadoDePedido estadoActual;
	List <Item> carrito = new ArrayList <>();
	Direccion direccionDeEntrega;
	
	public double precioAPagar() {
		return carrito.stream().mapToDouble(item->item.precioBaseCalculado()).sum();
		
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
	
	public double getPesoTotal() {
		return return carrito.stream().mapToDouble(item->item.getPeso()).sum();
	}
	
	public Direccion getDireccionDeEntrega() {
		return direccionDeEntrega;
	}
	
	public List<Item> getCarrito() {
		return carrito;
	}
	
}


