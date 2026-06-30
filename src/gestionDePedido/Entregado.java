package gestionDePedido;

public class Entregado extends EstadoDePedido{
	public Entregado(Pedido pedido) {
		super(pedido);
		setTipo(TipoDeEstado.ENTREGADO);
	}
}
