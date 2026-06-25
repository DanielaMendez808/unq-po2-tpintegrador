package ecommerce;

public class EnvioEstandar implements MetodoDeEnvio {
	
	public float calcularCosto(Pedido pedido) {
		return CorreoArgentina.estimarEnvio(pedido.getPesoTotal(),
				                            pedido.getDireccionDeEntrega()); //duda adapter
	}
	
	public int diasEstimados(Pedido pedido) {
		// entre 5 y 7
		return 7;
	}

}
