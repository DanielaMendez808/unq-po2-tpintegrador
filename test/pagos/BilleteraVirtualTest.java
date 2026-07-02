package pagos;

import static org.junit.jupiter.api.Assertions.*;
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

class BilleteraVirtualTest {

	private BilleteraVirtualAPI api;
    private Pedido pedido;
    private Usuario jose;
    private BilleteraVirtual billeteraVirtual;
    private final String cuenta = "MP Jose";
    private final double monto = 1000.0;
    private final String id = "999";

    @BeforeEach
    void setUp() {
    	
    	api = mock(BilleteraVirtualAPI.class);
		pedido = mock(Pedido.class);
        jose = mock(Usuario.class);
        billeteraVirtual = new BilleteraVirtual(api);
        
        when(pedido.getUsuario()).thenReturn(jose);
        when(jose.getBilleteraVirtual()).thenReturn(cuenta);
        
    }
    
    @Test
    void testProcesarPagoExitoso() {
        when(api.saldoSuficiente(cuenta)).thenReturn(true);
        when(api.bloqueoDeSaldo(cuenta, monto)).thenReturn(true);
        when(api.acreditarAlVendedor(cuenta, monto)).thenReturn(id);

        billeteraVirtual.procesarPago(pedido, monto);

        verify(api).enviarNotificacionPush(cuenta, id);
    }
    
    @Test
    void testProcesarPagoError() {
        when(api.saldoSuficiente(cuenta)).thenReturn(false);

        assertThrows(IllegalArgumentException.class, () -> {
            billeteraVirtual.procesarPago(pedido, monto);
        });

        verify(api, never()).bloqueoDeSaldo(anyString(), anyDouble());
    }
    
}
