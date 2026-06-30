package gestionDePedido;

public class Confirmado extends EstadoDePedido{
	public Confirmado(Pedido pedido) {
		super(pedido);
		setTipo(TipoDeEstado.CONFIRMADO);
	}
	public void preparar() {
		pedido.setEstadoDePedido(new EnPreparacion(pedido));
	}
	public void cancelar() {
		pedido.reponerStock();
		pedido.setEstadoDePedido(new Cancelado(pedido));
	}
	
}
