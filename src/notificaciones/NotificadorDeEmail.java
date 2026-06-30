package notificaciones;

import gestionDePedido.EstadoDePedido;
import gestionDePedido.Pedido;
import gestionDePedido.TipoDeEstado;

public class NotificadorDeEmail implements PedidoObserver {
	
	private MailSender mailSender;
	
	public NotificadorDeEmail(MailSender mailSender) {
        this.mailSender = mailSender;
    }
	
	public void actualizar(Pedido pedido, EstadoDePedido anterior, EstadoDePedido nuevo) {
		if (nuevo.getTipo() == TipoDeEstado.CONFIRMADO ||
			nuevo.getTipo() == TipoDeEstado.ENVIADO ||
			nuevo.getTipo() == TipoDeEstado.ENTREGADO) {
            this.enviarCorreo(pedido, anterior, nuevo);
        }
		
	}
	
	public void enviarCorreo(Pedido pedido, EstadoDePedido anterior, EstadoDePedido nuevo) {
		mailSender.enviarMail(pedido.getUsuario().getEmail(),
	              "Su pedido " + pedido.getId() + " se encuentra " + nuevo.getTipo(), 
	              "El estado de su pedido paso de: " + anterior.getTipo() +
	              " a " + nuevo.getTipo(),
	              null);
	}

}
