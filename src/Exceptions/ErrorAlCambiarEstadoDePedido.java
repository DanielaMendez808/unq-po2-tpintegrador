package Exceptions;

public class ErrorAlCambiarEstadoDePedido extends BussinessException {
		private static final long serialVersionUID = 1L;

		public ErrorAlCambiarEstadoDePedido(String mensaje) {
			super(mensaje);
		}
}
