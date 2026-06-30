package notificaciones;

import pagos.Comprobante;

public interface MailSender {
	
	void enviarMail(String direccionDestino, String titulo, String mensaje, Comprobante adjunto);

}
