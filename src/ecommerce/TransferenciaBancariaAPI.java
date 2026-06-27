package ecommerce;

import java.time.LocalDate;

public interface TransferenciaBancariaAPI {
	
	public boolean validarCBUOAlias(String cbuOAlias);
	public String ejecutarTransferencia(String cbuOAlias, double monto, boolean esInmediata, LocalDate fechaprogramada);

}
