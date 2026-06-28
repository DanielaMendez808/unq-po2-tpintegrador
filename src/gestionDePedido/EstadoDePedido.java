package gestionDePedido;

public class EstadoDePedido { //mantener como abstract class?? creo que no hace falta. Que diferencia hay? Pensarlo a nivel mas teorico
	Pedido pedido;
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
}
