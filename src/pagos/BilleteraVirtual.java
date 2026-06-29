package pagos;

import gestionDePedido.Pedido;

public class BilleteraVirtual extends MetodoDePago {

	private BilleteraVirtualAPI api;

    public BilleteraVirtual(BilleteraVirtualAPI api) {
        this.api = api;
    }
	
	@Override
	protected void validarDatos(Pedido pedido) {
		if (!api.saldoSuficiente(pedido.getUsuario().getBilleteraVirtual())) {
			throw new IllegalArgumentException("Saldo insuficiente.");
		}
	}
	
	@Override
    protected void reservarFondos(Pedido pedido, double monto) {
		if (!api.bloqueoDeSaldo(pedido.getUsuario().getBilleteraVirtual(), monto)) {
			throw new IllegalArgumentException("Error al bloquear fondos.");
		}
    }
	
	@Override
    protected String ejecutarTransaccion(Pedido pedido, double monto) {
		return api.acreditarAlVendedor(pedido.getUsuario().getBilleteraVirtual(), monto);
	}

	@Override
	protected void notificarResultado(Pedido pedido, String códigoTransacción, double monto) {
		api.enviarNotificacionPush(pedido.getUsuario().getBilleteraVirtual(), códigoTransacción);
    }
	
}
