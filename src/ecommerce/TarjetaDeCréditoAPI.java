package ecommerce;

public interface TarjetaDeCréditoAPI {
	
	public boolean validarTarjeta(String número, String cvv, String vencimiento);
	public boolean preAutorizar(String número, double monto);
	public String ejecutarTransacción(String número, double monto);

}
