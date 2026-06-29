package gestionDePedido;

public class RetiroEnSucursal implements MetodoDeEnvio {
	
	private Sucursal sucursal;
	
	public RetiroEnSucursal(Sucursal sucursal) {
		this.sucursal = sucursal;
	}
	
	public float calcularCosto(Pedido pedido) {
		return 0;
	}
	
	public int diasEstimados(Pedido pedido) {
		if (hayStockDeTodo(pedido)) {
			return 0;
		} else 
			return 3;
	}
	
	public boolean hayStockDeTodo(Pedido pedido) {
		return pedido.getCarrito().stream()
				.allMatch(item -> item.getStockEnSucursal(pedido.getSucursal()) > 0);
	}

	public Sucursal getSucursal() {
		return this.sucursal;
	}

	
}
