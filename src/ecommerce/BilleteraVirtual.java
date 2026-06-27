package ecommerce;

public class BilleteraVirtual extends MetodoDePago {

	private BilleteraVirtualAPI api;
    private String cuenta;

    public BilleteraVirtual(BilleteraVirtualAPI api, String cuenta) {
        this.api = api;
        this.cuenta = cuenta;
    }
	
	@Override
	protected void validarDatos() {
		if (!api.saldoSuficiente(cuenta)) {
			throw new IllegalArgumentException("Saldo insuficiente.");
		}
	}
	
	@Override
    protected void reservarFondos(double monto) {
		if (!api.bloqueoDeSaldo(getCuenta(), monto)) {
			throw new IllegalArgumentException("Error al bloquear fondos.");
		}
    }
	
	@Override
    protected String ejecutarTransaccion(double monto) {
		return api.acreditarAlVendedor(getCuenta(), monto);
	}

	@Override
	protected void notificarResultado(String códigoTransacción) {
		api.enviarNotificacionPush(getCuenta(), códigoTransacción);
    }

	public String getCuenta() {
		return cuenta;
	}
	
}
