package reportes;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import catalogoEItems.Item;
import gestionDePedido.Pedido;
import gestionDePedido.TipoDeEstado;

public class ReporteDeProductosMasVendidos implements Reporte {
	
	private LocalDate desde;
	private LocalDate hasta;
	private List<Pedido> todosLosPedidos;
	
	public ReporteDeProductosMasVendidos(LocalDate desde, LocalDate hasta, List<Pedido> todosLosPedidos) {
        this.desde = desde;
        this.hasta = hasta;
        this.todosLosPedidos = todosLosPedidos;
    }
	
	public List<ItemVendido> ordenarProductosMasVendidos() {
		List<Pedido> validos = filtrarPedidosEnPeriodo(todosLosPedidos);
		Map<String, ItemVendido> mapIV = new HashMap<>();
		for (Pedido pedido : validos) {
			for (Item item : pedido.getCarrito()) {
				String nombre = item.getNombre();
				if (!mapIV.containsKey(nombre)) {
					mapIV.put(nombre, new ItemVendido(item));
				} else {
					mapIV.get(nombre).acumularVenta(item);
				}
			}
		}
		return ordenarPorCantidadesVendidas(mapIV);
	}
	
	public List<Pedido> filtrarPedidosEnPeriodo(List<Pedido> pedidos) {
	    return pedidos.stream()
	    		.filter(p -> p.getEstadoDePedido().getTipo() == TipoDeEstado.ENTREGADO)
	            .filter(p -> !p.getFecha().isBefore(getDesde()) && !p.getFecha().isAfter(getHasta()))
	                         //no está antes inclusivo        //no está después inclusivo
	            .toList();
	}
	
	public List<ItemVendido> ordenarPorCantidadesVendidas(Map<String, ItemVendido> itemsVendidos) {
		List<ItemVendido> listaOrdenada = new ArrayList<>(itemsVendidos.values());
		listaOrdenada.sort((a, b) -> Integer.compare(b.getCantUnidades(), a.getCantUnidades())); //(b - a) mayor a menor
		return listaOrdenada;
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
