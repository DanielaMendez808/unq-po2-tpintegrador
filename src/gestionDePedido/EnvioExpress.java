package gestionDePedido;

public class EnvioExpress implements MetodoDeEnvio {
	
	private float porcentaje;
	private float cargoBase;
	
	public EnvioExpress(float porcentaje, float cargoBase) {
		this.porcentaje = porcentaje;
		this.cargoBase = cargoBase;
	}
	
	public float calcularCosto(Pedido pedido) {
		return (float) pedido.precioAPagar() * (porcentaje / 100) + cargoBase;
	}
	
	public int diasEstimados(Pedido pedido) {
		return 1;
	}
	
	public TipoDeMetodo getTipo() {
		return TipoDeMetodo.EXPRESS;
	}

}
