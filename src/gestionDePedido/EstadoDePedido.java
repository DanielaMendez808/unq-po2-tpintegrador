package gestionDePedido;

import Exceptions.ErrorAlCambiarEstadoDePedido;

public abstract class EstadoDePedido {
	
	protected Pedido pedido;
	protected TipoDeEstado tipo;
	
	public EstadoDePedido(Pedido pedido) {
		super();
		this.pedido=pedido;
	}
	public void confirmar() {
		throw new ErrorAlCambiarEstadoDePedido("No se puede confirmar el pedido desde este estado");
	}
	public void cancelar() {
		throw new ErrorAlCambiarEstadoDePedido("No se puede cancelar el pedido desde este estado");
	}
	public void preparar() {
		throw new ErrorAlCambiarEstadoDePedido("No se puede preparar el pedido desde este estado");
	}
	public void enviar() {
		throw new ErrorAlCambiarEstadoDePedido("No se puede enviar  el pedido desde este estado");
	}
	public void entregar() {
		throw new ErrorAlCambiarEstadoDePedido("No se puede entregar  el pedido desde este estado");
	}
	
	public TipoDeEstado getTipo() {
		return tipo;
	}
	
	public void setTipo(TipoDeEstado tipo) {
		this.tipo = tipo;
	}

}
