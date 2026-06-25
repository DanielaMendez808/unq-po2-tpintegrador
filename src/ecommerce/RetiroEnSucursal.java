package ecommerce;

public class RetiroEnSucursal implements MetodoDeEnvio {
	
	private Sucursal sucursal;
	
	public RetiroEnSucursal(Sucursal sucursal) {
		this.sucursal = sucursal;
	}
	
	public float calcularCosto(Pedido pedido) {
		return 0;
	}
	
	public int diasEstimados(Pedido pedido) {
		if (sucursal.hayStock(pedido.getCarrito())) {
			return 0;
		} else return 3;
	}

}
