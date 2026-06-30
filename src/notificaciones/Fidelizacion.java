package notificaciones;

import gestionDePedido.EstadoDePedido;
import gestionDePedido.Pedido;
import gestionDePedido.TipoDeEstado;
import pagos.Comprobante;
import pagos.CuponDeDescuento;

public class Fidelizacion implements PedidoObserver {
	
	private MailSender mailSender;
	
	public Fidelizacion(MailSender mailSender) {
        this.mailSender = mailSender;
    }
	
	public void actualizar(Pedido pedido, EstadoDePedido anterior, EstadoDePedido nuevo) {
        if (nuevo.getTipo() == TipoDeEstado.CANCELADO) {
            this.enviarCuponDescuento(pedido, anterior, nuevo);
        }
    }
    
    private void enviarCuponDescuento(Pedido pedido, EstadoDePedido anterior, EstadoDePedido nuevo)  {
    	Comprobante cupon = new CuponDeDescuento(pedido.getId(), 5);
		mailSender.enviarMail(pedido.getUsuario().getEmail(),
	              "Termina tu pedido con este cupón", 
	              "Notamos que tu pedido paso de " + anterior.getTipo() +
	              " a " + nuevo.getTipo() + ". Por eso te entregamos este cupón. ¡Disfrútalo!",
	              cupon);
	}

}
