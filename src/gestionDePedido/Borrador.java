package gestionDePedido;

import catalogoEItems.Item;

public class Borrador extends EstadoDePedido{
	public Borrador(Pedido pedido){
		super(pedido);
		setTipo(TipoDeEstado.BORRADOR);
	}
	public void agregarItem(Item item ) {
		item.validarQueHayStockDelItem();
		pedido.getCarrito().add(item);
		item.decrementarStock();
	}
	public void quitarItem(Item item) {
		pedido.validarQueElItemEstaEnElCarrito(item);
		item.incrementarStock();
		pedido.getCarrito().remove(item);
		
	}
	// si el cliente eligio retiro en sucursal, la revision de si hay stock se hace cuando se confirma 
	public void confirmar() {
		if (pedido.getMetodoDeEnvio() instanceof RetiroEnSucursal) {
			pedido.decrementarStock(pedido.getSucursal());
			pedido.setEstadoDePedido(new Confirmado(pedido));
		} else {
		pedido.decrementarStock();
		pedido.setEstadoDePedido(new Confirmado(pedido));
		}
	}
	public void cancelar() {
		pedido.setEstadoDePedido(new Cancelado(pedido));
	}

}
