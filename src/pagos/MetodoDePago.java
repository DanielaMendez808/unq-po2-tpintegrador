package pagos;

import gestionDePedido.Pedido;

public abstract class MetodoDePago {
	
	public final void procesarPago(Pedido pedido, double monto) {
        validarDatos(pedido);
        reservarFondos(pedido, monto);
        String númeroDeOperación = ejecutarTransaccion(pedido, monto);
        notificarResultado(pedido, númeroDeOperación, monto);
	}
	
	protected abstract void validarDatos(Pedido pedido);
    protected abstract void reservarFondos(Pedido pedido, double monto);
    protected abstract String ejecutarTransaccion(Pedido pedido, double monto);
    
    protected void notificarResultado(Pedido pedido, String códigoTransacción, double monto) {
    	pedido.setId(códigoTransacción);
    } 

}
