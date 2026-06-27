package ecommerce;

import java.time.LocalDate;

public class TransferenciaBancaria extends MetodoDePago {
	
	private TransferenciaBancariaAPI api;
    private String cbuOAlias;
    private boolean esInmediata;
    private LocalDate fechaAProgramar;

    public TransferenciaBancaria(TransferenciaBancariaAPI api, String cbuOAlias, boolean esInmediata) {
        this.api = api;
        this.cbuOAlias = cbuOAlias;
        this.esInmediata = esInmediata;
    }
	
	@Override
	protected void validarDatos() {
		if (!api.validarCBUOAlias(getCbuOAlias())) {
			throw new IllegalArgumentException("CBU o Alias inválido.");
		}
	}
	
	@Override
    protected void reservarFondos(double monto) {
		//no aplica
    }
	
	@Override
    protected String ejecutarTransaccion(double monto) {
		return api.ejecutarTransferencia(getCbuOAlias(), monto, isEsInmediata(), getFechaAProgramar()); //null fecha si es inmediata
	}
	
	@Override
	protected void notificarResultado(String códigoTransacción) {
		if (isEsInmediata()) {
			super.notificarResultado(códigoTransacción);
		}
        //implementar programada;
    }

	public String getCbuOAlias() {
		return cbuOAlias;
	}

	public boolean isEsInmediata() {
		return esInmediata;
	}

	public LocalDate getFechaAProgramar() {
		return fechaAProgramar;
	}
	
}
