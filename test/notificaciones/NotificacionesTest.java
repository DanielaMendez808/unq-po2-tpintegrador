package notificaciones;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.contains;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import gestionDePedido.EstadoDePedido;
import gestionDePedido.Pedido;
import gestionDePedido.TipoDeEstado;
import gestionDePedido.Usuario;
import pagos.CuponDeDescuento;
import pagos.Factura;

class NotificacionesTest {

	private MailSender mailSender;
    private NotificadorDeEmail notificador;
    private GeneradorDeFactura generador;
    private Fidelizacion fidelizacion;
    private Pedido pedido;
    private Usuario jose;
    private EstadoDePedido estadoAnterior;
	
	@BeforeEach
	public void setUp() {
		
		mailSender = mock(MailSender.class);
		pedido = mock(Pedido.class);
        jose = mock(Usuario.class);
        estadoAnterior = mock(EstadoDePedido.class);
        
        when(pedido.getUsuario()).thenReturn(jose);
        when(jose.getEmail()).thenReturn("test@gmail.com");
        when(pedido.getId()).thenReturn("123");
        notificador = new NotificadorDeEmail(mailSender);
        generador = new GeneradorDeFactura();
        fidelizacion = new Fidelizacion(mailSender);
		
	}
	
	@Test
    public void testNotificadorEstadoConfirmadoMail() {
        EstadoDePedido estadoNuevo = mock(EstadoDePedido.class);
        when(estadoNuevo.getTipo()).thenReturn(TipoDeEstado.CONFIRMADO);
        when(estadoAnterior.getTipo()).thenReturn(TipoDeEstado.ENPREPARACION);

        notificador.actualizar(pedido, estadoAnterior, estadoNuevo);

        verify(mailSender, times(1)).enviarMail(
            eq("test@gmail.com"),
            contains("CONFIRMADO"),
            contains("ENPREPARACION"),
            isNull());
    }
	
	@Test
    public void testNotificadorEstadoCanceladoSinMail() {
        EstadoDePedido estadoNuevo = mock(EstadoDePedido.class);
        when(estadoNuevo.getTipo()).thenReturn(TipoDeEstado.CANCELADO);
        
        notificador.actualizar(pedido, estadoAnterior, estadoNuevo);

        verify(mailSender, never()).enviarMail(anyString(), anyString(), anyString(), any());
	}
	
	@Test
    public void testFidelizacionEstadoCanceladoCupon() {
        EstadoDePedido estadoNuevo = mock(EstadoDePedido.class);
        when(estadoNuevo.getTipo()).thenReturn(TipoDeEstado.CANCELADO);
        when(estadoAnterior.getTipo()).thenReturn(TipoDeEstado.CONFIRMADO);

        fidelizacion.actualizar(pedido, estadoAnterior, estadoNuevo);

        verify(mailSender, times(1)).enviarMail(
            eq("test@gmail.com"),
            eq("Termina tu pedido con este cupón"),
            contains("CANCELADO"),
            any(CuponDeDescuento.class));
    }
	
	@Test
    public void testGeneradorEstadoEntregadoFactura() {
        EstadoDePedido estadoNuevo = mock(EstadoDePedido.class);
        when(estadoNuevo.getTipo()).thenReturn(TipoDeEstado.ENTREGADO);

        generador.actualizar(pedido, estadoAnterior, estadoNuevo);

        verify(jose, times(1)).agregarComprobante(any(Factura.class));
    }
	
}

