package gestionDePedido;

public interface MetodoDeEnvio {
	
	public float calcularCosto(Pedido pedido);
	public int diasEstimados(Pedido pedido);

}
