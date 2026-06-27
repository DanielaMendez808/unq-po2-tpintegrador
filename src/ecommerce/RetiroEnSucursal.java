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
	//creo sucursal1 con un teclado
	//stockSucursal1= 1 teclado
	//el usuario ve en el catalogo que hay dos teclados en deposito
	//stockDeposito = 2 teclados
	//el usuario pide el pedido de dos teclado
	//cuando decida el metodo de envio en sucursal, se chequea si la sucursal elegida tiene dos teclados
	//no tiene el total del pedido
	//los dias estimado son 3
	
	public Sucursal getSucursal() {
		return this.sucursal;
	}

	
}
