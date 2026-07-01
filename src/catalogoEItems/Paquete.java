package catalogoEItems;

import java.util.ArrayList;
import java.util.List;

import Exceptions.ErrorDeEdicionDePaquete;
import Exceptions.ErrorDeStockInsuficiente;
import gestionDePedido.Sucursal;

public class Paquete extends Item {
	private List <Item> itemsDelPaquete = new ArrayList<>();
///////////CONSTRUCTOR/////////////
	public Paquete(String nombre, String descripcion, ArrayList<Item> itemsDelPaquete, double descuento, String categoria, Sucursal sucursal) {
		super(
				nombre, 
				descripcion, 
				itemsDelPaquete.stream().mapToDouble(item ->item.precioBaseCalculado()).sum(), 
				descuento,
				categoria,
				itemsDelPaquete.stream().mapToDouble(item ->item.getPeso()).sum(),
				sucursal
				
		);
		this.itemsDelPaquete =itemsDelPaquete;
	}
	///////STOCK//////////
	@Override
	public boolean tieneStock() {
		return (getStock() > 0);
	}
	
	public void validarQueHayStockDelItem() {
		if(!this.tieneStock()) {
			throw new ErrorDeStockInsuficiente("No hay stock de " +this.getNombre());
		}
	}
	
	public int getStock() {
        // El stock del paquete es el mínimo stock disponible de entre sus componentes
        return itemsDelPaquete.stream()
                    .mapToInt(Item::getStock)
                    .min()
                    .orElse(0);
    }
	
	public int getStockEnSucursal(Sucursal sucursal) {
        if (itemsDelPaquete.isEmpty()) return 0;
        return itemsDelPaquete.stream()
                    .mapToInt(item -> item.getStockEnSucursal(sucursal))
                    .min()
                    .orElse(0);
    }
	
	@Override
	public void incrementarStock(Sucursal sucursal) {
        for (Item item : itemsDelPaquete) {
            item.incrementarStock(sucursal);
        }
    }
	
	@Override
	public void incrementarStock() {
		itemsDelPaquete.stream().forEach(Item::incrementarStock);
	}
	
	public void validarQueExisteStockParaArmarPaquete() {
		if(!this.existeStockDeCadaItemEnPaquete()) {
			throw new ErrorDeStockInsuficiente("Error: No hay stock para armar el paquete " + this.getNombre());
		}
	}
	public boolean existeStockDeCadaItemEnPaquete() {
		return itemsDelPaquete.stream().allMatch(item ->item.tieneStock());
	}
	
	@Override
    public void decrementarStock(Sucursal sucursal) {
		if(!this.existeStockDeCadaItemEnPaquete()) {
			throw new ErrorDeStockInsuficiente("Error: No hay stock para armar el paquete " + this.getNombre());
		}
        for (Item item : itemsDelPaquete) {
            item.decrementarStock(sucursal);
        }
    }
	
	@Override
	public void decrementarStock() {
		itemsDelPaquete.stream().forEach(Item::decrementarStock);
	}
	/////PRECIO/////////
	/// 
	////////////AGREGAR Y BORRAR ITEMS DE PAQUETE///////
	public void agregarItemAPaquete(Item item) {
		this.verificarQueElItemNoEstaEnElPaquete(item);
		this.setPeso(this.getPeso()+item.getPeso());
		this.setPrecioInicial(this.getPrecioInicial()+item.getPrecioInicial());
		itemsDelPaquete.add(item);
	}
	public void verificarQueElItemNoEstaEnElPaquete(Item item) {
		if(itemEstaEnPaquete(item)) {
			throw new ErrorDeEdicionDePaquete("Error: El item ya esta en el paquete");
		}
	}
	public boolean itemEstaEnPaquete(Item item) {
		return itemsDelPaquete.contains(item);
	}
	public void eliminarItemDePaquete(Item item) {
		validarQueElItemExisteEnElPaquete(item);
		this.setPeso(this.getPeso()-item.getPeso());
		this.setPrecioInicial(this.getPrecioInicial()-item.getPrecioInicial());
		itemsDelPaquete.remove(item);
	}
	public void validarQueElItemExisteEnElPaquete(Item item) {
		if(!itemEstaEnPaquete(item)) {
			throw new ErrorDeEdicionDePaquete("Error: El item no esta en el carrito");
		}
	}
	/////////GETTERS Y SETTERS///////////

	public List<Item> getItemsDelPaquete() {
		return itemsDelPaquete;
	}
	
}
