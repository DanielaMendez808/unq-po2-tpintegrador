package gestionDePedido;

public class EnvioEstandar implements MetodoDeEnvio {
	
	public float calcularCosto(Pedido pedido) {
		return CorreoArgentina.estimarEnvio((float)pedido.getPesoTotal(),
				                            pedido.getDireccionDeEntrega());
	}
	
	public int diasEstimados(Pedido pedido) {
		// entre 5 y 7
		return 7;
	}
	
	public TipoDeMetodo getTipo() {
		return TipoDeMetodo.ESTANDAR;
	}

}
