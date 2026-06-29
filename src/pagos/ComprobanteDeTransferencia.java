package pagos;

import java.time.LocalDate;

public class ComprobanteDeTransferencia implements Comprobante {
	
	private String cbu;
    private String numeroOperacion;
    private double monto;
    private LocalDate fecha;

    public ComprobanteDeTransferencia(String cbu, String numeroOperacion, double monto, LocalDate fecha) {
		this.cbu=cbu;
		this.numeroOperacion=numeroOperacion;
		this.monto=monto;
		this.fecha=fecha;
	}
    
    @Override
    public String getCodigoTransaccion() {
    	return "Operación N°-" + numeroOperacion;
    }
    
    @Override
    public double getMonto() {
    	return this.monto; 
    }

	public String getCbu() {
		return cbu;
	}

	public LocalDate getFecha() {
		return fecha;
	}
    
}
