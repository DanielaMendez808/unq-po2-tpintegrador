package pagos;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import gestionDePedido.Pedido;
import gestionDePedido.Usuario;

class TarjetaDeCréditoTest {

	private TarjetaDeCréditoAPI api;
    private Pedido pedido;
    private Usuario jose;
    private TarjetaDeCrédito tarjetaDeCrédito;
    private final String tarjeta = "xxxx-xxxx-xxxx-9898";
    private final double monto = 1000.0;
    private final String id = "999";

    @BeforeEach
    void setUp() {
    	
    	api = mock(TarjetaDeCréditoAPI.class);
		pedido = mock(Pedido.class);
        jose = mock(Usuario.class);
        tarjetaDeCrédito = new TarjetaDeCrédito(api);
        
        when(pedido.getUsuario()).thenReturn(jose);
        when(jose.getTarjeta()).thenReturn(tarjeta);
        
    }
    
    @Test
    void testProcesarPagoExitoso() {
    	when(api.validarTarjeta(tarjeta)).thenReturn(true);
        when(api.preAutorizar(tarjeta, monto)).thenReturn(true);
        when(api.ejecutarTransacción(tarjeta, monto)).thenReturn(id);

        tarjetaDeCrédito.procesarPago(pedido, monto);

        verify(pedido).setId(id);
        verify(jose).agregarComprobante(any(CuponDePago.class));
    }
    
    @Test
    void testProcesarPagoError() {
    	when(api.validarTarjeta(tarjeta)).thenReturn(false);

        assertThrows(IllegalArgumentException.class, () -> {
        	tarjetaDeCrédito.procesarPago(pedido, monto);
        });
    }
    
}