package Exceptions;

public class ErrorDeStockInsuficiente extends BussinessException{
	private static final long serialVersionUID = 1L;

	public ErrorDeStockInsuficiente(String mensaje) {
		super(mensaje);
	}
}
