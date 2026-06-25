package ecommerce;

public class EnvioExpress implements MetodoDeEnvio {
	
	private float porcentaje;
	private float cargoBase;
	
	public EnvioExpress(float porcentaje, float cargoBase) {
		this.porcentaje = porcentaje;
		this.cargoBase = cargoBase;
	}
	
	public float calcularCosto(Pedido pedido) {
		return calcularCosto((float) pedido.precioAPagar());
	}
	
	public float calcularCosto(float precio) { //duda adapter/API
		return precio * (porcentaje / 100) + cargoBase;
	}
	
	
	public int diasEstimados(Pedido pedido) {
		return 1;
	}

}
