package reportes;

import java.time.LocalDate;
import java.util.List;

import gestionDePedido.Pedido;
import gestionDePedido.TipoDeEstado;

public class ReporteDeProductosMasVendidos implements Reporte {
	
	private LocalDate desde;
	private LocalDate hasta;
	
	public ReporteDeProductosMasVendidos(LocalDate desde, LocalDate hasta) {
        this.desde = desde;
        this.hasta = hasta;
    }
	
	public List<ItemVendido> ordenarProductosMasVendidos(List<Pedido> pedidos) {
		//filtrarLista
		//cantidadDeVendidos de un producto
		// cant * sumaPrecio / cant
		//crearLista de ItemsVendidos en orden		
	}
	
	public List<Pedido> filtrarPedidosEnPeriodo(List<Pedido> pedidos) {
	    return pedidos.stream()
	    		.filter(p -> p.getEstadoDePedido().getTipo() == TipoDeEstado.ENTREGADO)
	            .filter(p -> !p.getFecha().isBefore(desde) && !p.getFecha().isAfter(hasta))
	                         //no está antes inclusivo        //no está después inclusivo
	            .toList();
	}
	
	public void accept(ReporteVisitor visitor) {
		visitor.visit(this);
	}

	public LocalDate getDesde() {
		return desde;
	}

	public LocalDate getHasta() {
		return hasta;
	}

}
