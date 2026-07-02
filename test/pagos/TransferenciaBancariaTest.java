package pagos;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import gestionDePedido.Pedido;
import gestionDePedido.Usuario;

class TransferenciaBancariaTest {

	private TransferenciaBancariaAPI api;
    private Pedido pedido;
    private Usuario jose;
    private TransferenciaBancaria transferenciaBancaria;
    private final String cbu = "0000544556";
    private final double monto = 1000.0;
    private final String id = "999";

    @BeforeEach
    void setUp() {
    	
    	api = mock(TransferenciaBancariaAPI.class);
		pedido = mock(Pedido.class);
        jose = mock(Usuario.class);
        transferenciaBancaria = new TransferenciaBancaria(api, cbu, true);
        
        when(pedido.getUsuario()).thenReturn(jose);
        when(jose.getCbu()).thenReturn(cbu);
        
    }
    
    @Test
    void testProcesarPagoExitoso() {
    	when(api.validarCBUOAlias(cbu)).thenReturn(true);
        when(api.ejecutarTransferencia(cbu, monto, true, null)).thenReturn(id);

        transferenciaBancaria.procesarPago(pedido, monto);

        verify(pedido).setId(id);
        verify(jose).agregarComprobante(any(ComprobanteDeTransferencia.class));
    }
    
    @Test
    void testProcesarPagoError() {
    	when(api.validarCBUOAlias(cbu)).thenReturn(false);

        assertThrows(IllegalArgumentException.class, () -> {
        	transferenciaBancaria.procesarPago(pedido, monto);
        });
        
        verify(api, never()).ejecutarTransferencia(anyString(), anyDouble(), anyBoolean(), any());
    }
    
}