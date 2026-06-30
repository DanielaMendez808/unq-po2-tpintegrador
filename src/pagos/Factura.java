package pagos;

public class Factura implements Comprobante {
	
    private String codigo;
    private double monto;

    public Factura(String codigo, double monto) {
		this.codigo=codigo;
		this.monto=monto;
	}
    
    @Override
    public String getCodigoTransaccion() {
    	return "Factura N°-" + codigo;
    }
    
    @Override
    public double getMonto() {
    	return this.monto; 
    }

}
