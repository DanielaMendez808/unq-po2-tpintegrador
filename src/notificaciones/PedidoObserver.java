package notificaciones;

import gestionDePedido.EstadoDePedido;
import gestionDePedido.Pedido;

public interface PedidoObserver {
	
	void actualizar(Pedido pedido, EstadoDePedido estadoAnterior, EstadoDePedido estadoNuevo);

}
