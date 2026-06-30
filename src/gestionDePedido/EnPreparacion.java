package gestionDePedido;

public class EnPreparacion extends EstadoDePedido {
	public EnPreparacion(Pedido pedido) {
		super(pedido);
		setTipo(TipoDeEstado.ENPREPARACION);
	}
	public void enviar() {
		pedido.setEstadoDePedido(new Enviado(pedido));
	}
	public void cancelar() {
		pedido.reembolsarCostoDeProductos(); //primero reembolsar porque sino pierdo la variable de costoTotal
		pedido.reembolsarCostoDeEnvio();
		pedido.reponerStock(); 
		pedido.setEstadoDePedido(new Cancelado(pedido));
	}
}
