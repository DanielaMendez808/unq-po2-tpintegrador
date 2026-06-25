package ecommerce;

public class EnPreparacion extends EstadoDePedido {
	public EnPreparacion(Pedido pedido) {
		super(pedido);
	}
	public void enviar() {
		pedido.setEstadoDePedido(new Enviado(pedido));
	}
	public void cancelar() {
		pedido.reponerStock()
		pedido.reembolsarCostoDeProductosYEnvio()
		pedido.setEstadoDePedido(new Cancelado(pedido));
	}
}
