package pagos;

public class CuponDePago implements Comprobante {
	
	private String codigo;
    private double monto;
    
    public CuponDePago(String codigo, double monto) {
		this.codigo=codigo;
		this.monto=monto;
	}
    
    @Override
    public String getCodigoTransaccion() {
    	return "Cupón de Pago N°-" + codigo;
    }
    
    @Override
    public double getMonto() {
    	return this.monto; 
    }

}
