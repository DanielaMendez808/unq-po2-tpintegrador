package pagos;

import java.time.LocalDate;

import gestionDePedido.Pedido;

public class TransferenciaBancaria extends MetodoDePago {
	
	private TransferenciaBancariaAPI api;
    private boolean esInmediata;
    private LocalDate fechaAProgramar;

    public TransferenciaBancaria(TransferenciaBancariaAPI api, String cbuOAlias, boolean esInmediata) {
        this.api = api;
        this.esInmediata = esInmediata;
    }
	
	@Override
	protected void validarDatos(Pedido pedido) {
		if (!api.validarCBUOAlias(pedido.getUsuario().getCbu())) {
			throw new IllegalArgumentException("CBU o Alias inválido.");
		}
	}
	
	@Override
    protected void reservarFondos(Pedido pedido, double monto) {
		//no aplica
    }
	
	@Override
    protected String ejecutarTransaccion(Pedido pedido, double monto) {
		return api.ejecutarTransferencia(pedido.getUsuario().getCbu(), monto, isEsInmediata(), getFechaAProgramar()); //null fecha si es inmediata
	}
	
	@Override
	protected void notificarResultado(Pedido pedido, String códigoTransacción, double monto) {
		super.notificarResultado(pedido, códigoTransacción, monto);
		ComprobanteDeTransferencia cTransferencia;
		if (isEsInmediata()) {
	    	cTransferencia = new ComprobanteDeTransferencia(
	    			pedido.getUsuario().getCbu(), códigoTransacción, monto, LocalDate.now());
		} else { 
			LocalDate fechaProgramada = LocalDate.now().plusDays(2);
			cTransferencia = new ComprobanteDeTransferencia(
	    			pedido.getUsuario().getCbu(), códigoTransacción, monto, fechaProgramada);
		}
		pedido.getUsuario().agregarComprobante(cTransferencia);
    }

	public boolean isEsInmediata() {
		return esInmediata;
	}

	public LocalDate getFechaAProgramar() {
		return fechaAProgramar;
	}
	
}
