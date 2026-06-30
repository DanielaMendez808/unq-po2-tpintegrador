package notificaciones;

import gestionDePedido.EstadoDePedido;
import gestionDePedido.Pedido;
import gestionDePedido.TipoDeEstado;
import pagos.Comprobante;
import pagos.Factura;

public class GeneradorDeFactura implements PedidoObserver {
	
	public void actualizar(Pedido pedido, EstadoDePedido anterior, EstadoDePedido nuevo) {
		if (nuevo.getTipo() == TipoDeEstado.ENTREGADO) {
            this.crearFactura(pedido);
        }
    }

    private void crearFactura(Pedido pedido) {
    	Comprobante factura = new Factura(pedido.getId(), pedido.precioAPagar());
    	pedido.getUsuario().agregarComprobante(factura);
    }
    
}
