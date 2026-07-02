package catalogoEItems;

import java.util.ArrayList;
import java.util.List;

import Exceptions.ErrorDeCatalogo;
import gestionDePedido.Pedido;
import reportes.Reporte;

public class AppEcommerce { //patron singleton
	// La única instancia que va a existir
    private static AppEcommerce instanciaUnica;
    private final List<Item> catalogo = new ArrayList<>();
    private final List<Pedido> historialDePedidos= new ArrayList <>();
    private final List<Reporte> reportes = new ArrayList <>();
    // Constructor PRIVADO: nadie desde afuera puede hacer "new Catalogo()"
    private AppEcommerce() {}

    // Método global para obtener la única instancia
    public static AppEcommerce getInstancia() {
        if (instanciaUnica == null) {
            instanciaUnica = new AppEcommerce();
        }
        return instanciaUnica;
    }
    ////MENSAJES PARA CONSTRUCTORES DE ITEM Y PEDIDO//////

    public void agregarItem(Item item) {
        this.getCatalogo().add(item);
    }
    public void agregarPedidoAHistorial(Pedido pedido) {
    	this.getHistorialDePedidos().add(pedido);
    }
    /////ELIMINAR ITEM DE CATALOGO///////////
    // no hago eliminar pedido de catalogo porque no tiene sentido si es un historial, necesito todos los pedidos que se hayan hecho.
	public void eliminarItemDelCatalogo(Item item) { //solo la funcion eliminar porque para agregar el item al catalogo ya se hace automaticamente en el constructor del item
		this.verificarQueElItemEsteEnElCatalogo(item);
		this.getCatalogo().remove(item);
	}
	public void verificarQueElItemEsteEnElCatalogo(Item item) {
		if(!this.itemEstaEnElCatalogo(item)) {
			throw new ErrorDeCatalogo("El item"+item.getNombre()+"no existe en el catalogo");
		}
	}
	public boolean itemEstaEnElCatalogo(Item item) {
		return this.getCatalogo().contains(item);
	}
	///GETTERS/////
	public List<Item> getCatalogo() {
		return catalogo;
	}
	
	public List<Pedido> getHistorialDePedidos() {
		return historialDePedidos;
	}

	public List<Reporte> getReportes() {
		return reportes;
	}
	
}
