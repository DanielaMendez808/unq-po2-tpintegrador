package gestionDePedido;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import catalogoEItems.AppEcommerce;
import catalogoEItems.Item;
import notificaciones.PedidoObserver;
import pagos.NotaDeCredito;

public class Pedido {
	private Usuario usuario;
	private MetodoDeEnvio metodoDeEnvio;
	private EstadoDePedido estadoActual;
	private List <Item> carrito = new ArrayList <>(); //admite items repetidos
	private Direccion direccionDeEntrega;
	private Sucursal sucursalElegida;
	private String idPago;
	private LocalDate fecha;
	private List<PedidoObserver> suscriptores = new ArrayList <>();
	
	public double precioAPagar() {
		return carrito.stream().mapToDouble(item->item.precioBaseCalculado()).sum();
		
	}
	
	public Pedido(Usuario usuario, MetodoDeEnvio metodoDeEnvio, EstadoDePedido estadoActual, List<Item> carrito,
			Direccion direccionDeEntrega, Sucursal sucursalElegida, String idPago, LocalDate fecha,
			List<PedidoObserver> suscriptores) {
		super();
		this.usuario = usuario;
		this.metodoDeEnvio = metodoDeEnvio;
		this.estadoActual = new Borrador(this);
		this.carrito = carrito;
		this.direccionDeEntrega = direccionDeEntrega; // la direccion de entrega es obligaria en todos los casos de metodo de entrega?
		this.sucursalElegida = sucursalElegida;
		this.idPago = idPago;
		this.fecha = fecha;
		this.suscriptores = suscriptores;
		AppEcommerce.getInstancia().agregarPedidoAHistorial(this);
	}

	/////////////////////ESTADO DE PEDIDO/////////////////////////////////////
	public void setEstadoDePedido(EstadoDePedido nuevoEstado) {
		EstadoDePedido anterior = this.estadoActual; //agregar al constructor estado inicial borrador
		this.estadoActual= nuevoEstado;
		this.notificar(anterior, nuevoEstado);
	}
	
	public EstadoDePedido getEstadoDePedido() {
		return estadoActual;
	}
	
	public void reponerStock() {
		carrito.stream().forEach(item->item.incrementarStock());
		carrito.clear();
	}
	public void decrementarStock() {
		carrito.stream().forEach(item->item.decrementarStock());
		
	}
	
	public void decrementarStock(Sucursal sucursal) {
		carrito.stream().forEach(item->item.decrementarStock(sucursal));
	}
	
	public void incrementarStock(Sucursal sucursal) {
		carrito.stream().forEach(item->item.incrementarStock(sucursal));
	}
	
	public void reembolsarCostoDeProductos(){
		usuario.agregarComprobante(new NotaDeCredito(getId(), precioAPagar()));
		
	}
	public void reembolsarCostoDeEnvio() {// el costo de productos vuelve a ser 0 porque la lista se vacia, pero el costo de envio tambien habria que cambiarlo?
		
		usuario.agregarComprobante(new NotaDeCredito(getId(), this.costoEnvio()));
	}
	public void validarQueElItemEstaEnElCarrito(Item item) {
		if(!this.getCarrito().contains(item)) {
			throw new RuntimeException ("No hay"+item.nombre()+ "en el carrito");
		}
	}
	
/////////////////////////////////////////////////////////////////////////////
	public float costoEnvio() {
        return metodoDeEnvio.calcularCosto(this);
    }
	
	public int diasEstimados() {
        return metodoDeEnvio.diasEstimados(this);
    }
	
	public MetodoDeEnvio getMetodoDeEnvio() {
		return metodoDeEnvio;
	}
	public void setMetodoDeEnvio(MetodoDeEnvio metodo, Sucursal sucursal) {
		this.metodoDeEnvio = metodo;
		this.sucursalElegida = sucursal; //si no usa sucursal es null
    }
	
	public double getPesoTotal() {
		return  carrito.stream().mapToDouble(item->item.peso()).sum();
	}
	
	public Direccion getDireccionDeEntrega() {
		return direccionDeEntrega;
	}
	
	public List<Item> getCarrito() {
		return carrito;
	}
	
	public Sucursal getSucursal() {
		return sucursalElegida;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}
	public String getId() {
		return idPago;
	}
	
	public void setId(String id) {
		this.idPago = id;
	}
	
	public LocalDate getFecha() {
		return fecha;
	}
	
	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}
	
	public List<PedidoObserver> getSuscriptores() {
		return suscriptores;
	}
	
	public void agregarSuscriptor(PedidoObserver suscriptor) {
		getSuscriptores().add(suscriptor);
	}
	
	public void eliminarSuscriptor(PedidoObserver suscriptor) {
		getSuscriptores().remove(suscriptor);
	}
	
	//private para que nadie modifique
	private void notificar(EstadoDePedido anterior, EstadoDePedido nuevo) {
		for(PedidoObserver suscriptor : getSuscriptores()) {
			suscriptor.actualizar(this, anterior, nuevo);
		}
	}
	
}
