package gestionDePedido;

public abstract class EstadoDePedido {
	
	protected Pedido pedido;
	protected TipoDeEstado tipo;
	
	public EstadoDePedido(Pedido pedido) {
		super();
		this.pedido=pedido;
	}
	public void confirmar() {
		throw new ConfirmacionException("No se puede confirmar desde este estado");
	}
	public void cancelar() {
		throw new CancelarException("No se puede cancelar desde este estado");
	}
	public void preparar() {
		
	}
	public void enviar() {
		
	}
	public void entregar() {
		
	}
	
	public TipoDeEstado getTipo() {
		return tipo;
	}
	
	public void setTipo(TipoDeEstado tipo) {
		this.tipo = tipo;
	}

}
