package gestionDePedido;
import java.util.ArrayList;
import java.util.List;

import catalogoEItems.Item;
import ecommerce.Direccion;
import ecommerce.Usuario;

public class Pedido {
	Usuario usuario;
	private MetodoDeEnvio metodoDeEnvio;
	EstadoDePedido estadoActual;
	List <Item> carrito = new ArrayList <>(); //admite items repetidos
	Direccion direccionDeEntrega;
	Sucursal sucursalElegida;
	
	public double precioAPagar() {
		return carrito.stream().mapToDouble(item->item.precioBaseCalculado()).sum();
		
	}
	/////////////////////ESTADO DE PEDIDO/////////////////////////////////////
	public void setEstadoDePedido(EstadoDePedido nuevoEstado) {
		this.estadoActual= nuevoEstado;
	}
	public void reponerStock() {
		carrito.stream().forEach(item->item.incrementarStock());
		carrito.clear();
	}
	public void decrementarStock() {
		carrito.stream().forEach(item->item.decrementarStock());
		
	}
	
	public void decrementarStock(Sucursal sucursal) {
		carrito.stream().forEach(item->item.decrementarStock(sucursal));
	}
	
	public void incrementarStock(Sucursal sucursal) {
		carrito.stream().forEach(item->item.incrementarStock(sucursal));
	}
	
	public void reembolsarCostoDeProductos(){
		usuario.nuevaNotaDeCredito(precioAPagar());
		
	}
	public void reembolsarCostoDeEnvio() {// el costo de productos vuelve a ser 0 porque la lista se vacia, pero el costo de envio tambien habria que cambiarlo?
		
		usuario.nuevaNotaDeCredito(this.costoEnvio());
	}
	public void validarQueElItemEstaEnElCarrito(Item item) {
		if(!this.getCarrito().contains(item)) {
			throw new RuntimeException ("No hay"+item.nombre()+ "en el carrito");
		}
	}
	
/////////////////////////////////////////////////////////////////////////////
	public float costoEnvio() {
        return metodoDeEnvio.calcularCosto(this);
    }
	
	public int diasEstimados() {
        return metodoDeEnvio.diasEstimados(this);
    }
	
	public MetodoDeEnvio getMetodoDeEnvio() {
		return metodoDeEnvio;
	}
	public void setMetodoDeEnvio(MetodoDeEnvio metodo, Sucursal sucursal) {
		this.metodoDeEnvio = metodo;
		this.sucursalElegida = sucursal; //si no usa sucursal es null
    }
	
	public double getPesoTotal() {
		return  carrito.stream().mapToDouble(item->item.peso()).sum();
	}
	
	public Direccion getDireccionDeEntrega() {
		return direccionDeEntrega;
	}
	
	public List<Item> getCarrito() {
		return carrito;
	}
	
	public Sucursal getSucursal() {
		return sucursalElegida;
	}
	
}
