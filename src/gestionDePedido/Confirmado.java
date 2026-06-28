package gestionDePedido;

public class Confirmado extends EstadoDePedido{
	public Confirmado(Pedido pedido) {
		super(pedido);
	}
	public void preparar() {
		pedido.setEstadoDePedido(new EnPreparacion(pedido));
	}
	public void cancelar() {
		pedido.reponerStock(sucursal);
		pedido.setEstadoDePedido(new Cancelado(pedido));
	}
	
}
