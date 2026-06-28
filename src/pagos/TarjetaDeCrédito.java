package pagos;

public class TarjetaDeCrédito extends MetodoDePago {
	
	private TarjetaDeCréditoAPI api;
    private String número;
    private String cvv;
    private String vencimiento;

    public TarjetaDeCrédito(TarjetaDeCréditoAPI api, String número, String cvv, String vencimiento) {
        this.api = api;
        this.número = número;
        this.cvv = cvv;
        this.vencimiento = vencimiento;
    }
	
	@Override
	protected void validarDatos() {
		if (!api.validarTarjeta(getNúmero(), getCvv(), getVencimiento())) {
			throw new IllegalArgumentException("Datos de tarjeta inválidos.");
		}
	}
	
	@Override
    protected void reservarFondos(double monto) {
		if (!api.preAutorizar(getNúmero(), monto)) {
			throw new IllegalArgumentException("Pre-autorización rechazada.");
		}
    }
	
	@Override
    protected String ejecutarTransaccion(double monto) {
		return api.ejecutarTransacción(getNúmero(), monto);
	}

	public String getNúmero() {
		return número;
	}

	public String getCvv() {
		return cvv;
	}

	public String getVencimiento() {
		return vencimiento;
	}

}
