package ecommerce;

public abstract class EstadoDePedido { //mantener como abstract class??
	Pedido pedido;
	public EstadoDePedido(Pedido pedido) {
		super();
		this.pedido=pedido;
	}
	public void confirmar() {
		throw new OperacionInvalidaException("No se puede confirmar desde este estado");
	}
	public void cancelar() {
		
	}
	public void preparar() {
		
	}
	public void enviar() {
		
	}
	public void entregar() {
		
	}
}
