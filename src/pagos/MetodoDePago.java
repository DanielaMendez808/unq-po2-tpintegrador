package pagos;

public abstract class MetodoDePago {
	
	public final void procesarPago(double monto) {
        validarDatos();
        reservarFondos(monto);
        String númeroDeOperación = ejecutarTransaccion(monto);
        notificarResultado(númeroDeOperación);
	}
	
	protected abstract void validarDatos();
    protected abstract void reservarFondos(double monto);
    protected abstract String ejecutarTransaccion(double monto);
    
    protected void notificarResultado(String códigoTransacción) {
    	//implementar registro de comprobantes - Documentos/comprobantes como NotaDeCredito
    	                                       //I o Sclase
    } 

}
