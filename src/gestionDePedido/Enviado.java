package gestionDePedido;

import java.time.LocalDate;

public class Enviado extends EstadoDePedido {
	public Enviado(Pedido pedido) {
		super(pedido);
		setTipo(TipoDeEstado.ENVIADO);
	}
	public void entregar() {
		pedido.setEstadoDePedido(new Entregado(pedido));
		pedido.setFecha(LocalDate.now());
	}
	public void cancelar() {
		pedido.reembolsarCostoDeProductos();
		pedido.setEstadoDePedido(new Cancelado(pedido));
	}
}
