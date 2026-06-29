package pagos;

public interface TarjetaDeCréditoAPI {
	
	public boolean validarTarjeta(String tarjeta);
	public boolean preAutorizar(String tarjeta, double monto);
	public String ejecutarTransacción(String tarjeta, double monto);

}
