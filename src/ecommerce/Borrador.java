package ecommerce;

public class Borrador extends EstadoDePedido{
	public Borrador(Pedido pedido){
		super(pedido);
	}
	public void agregarItem(Item item ) {
		
	}
	public void quitarItem(Item item) {
		
	}
	public void confirmar() {
		pedido.decrementarStock()
		pedido.setEstadoDePedido(new Confirmado(pedido));
	}
	public void cancelar() {
		pedido.setEstadoDePedido(new Cancelado(pedido));
	}
	
	

}
