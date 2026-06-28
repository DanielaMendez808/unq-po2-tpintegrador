package pagos;

public interface BilleteraVirtualAPI {
	
	public boolean saldoSuficiente(String cuenta);
	public boolean bloqueoDeSaldo(String cuenta, Double saldo);
	public String acreditarAlVendedor(String cuenta, double monto);
	public String enviarNotificacionPush(String cuentaId, String notificación);

}