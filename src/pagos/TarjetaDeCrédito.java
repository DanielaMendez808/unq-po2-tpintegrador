package pagos;

import gestionDePedido.Pedido;

public class TarjetaDeCrédito extends MetodoDePago {
	
	private TarjetaDeCréditoAPI api;

    public TarjetaDeCrédito(TarjetaDeCréditoAPI api) {
        this.api = api;
    }
	
	@Override
	protected void validarDatos(Pedido pedido) {
		if (!api.validarTarjeta(pedido.getUsuario().getTarjeta())) {
			throw new IllegalArgumentException("Datos de tarjeta inválidos.");
		}
	}
	
	@Override
    protected void reservarFondos(Pedido pedido, double monto) {
		if (!api.preAutorizar(pedido.getUsuario().getTarjeta(), monto)) {
			throw new IllegalArgumentException("Pre-autorización rechazada.");
		}
    }
	
	@Override
    protected String ejecutarTransaccion(Pedido pedido, double monto) {
		return api.ejecutarTransacción(pedido.getUsuario().getTarjeta(), monto);
	}
	
	protected void notificarResultado(Pedido pedido, String códigoTransacción, double monto) {
    	super.notificarResultado(pedido, códigoTransacción, monto);
    	CuponDePago cPago = new CuponDePago(códigoTransacción, monto);
    	pedido.getUsuario().agregarComprobante(cPago);
    } 

}
