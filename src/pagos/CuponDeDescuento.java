package pagos;

public class CuponDeDescuento implements Comprobante {
	
	private String codigo;
	private double porcentaje;
	
	public CuponDeDescuento(String codigo, double porcentaje) {
		this.codigo=codigo;
		this.porcentaje=porcentaje;
	}
	
	@Override
    public String getCodigoTransaccion() {
    	return "Cupón de descuento N°-" + codigo;
    }
    
    @Override
    public double getMonto() {
    	return this.porcentaje; 
    }

}
