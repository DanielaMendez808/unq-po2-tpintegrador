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
	private List <Item> carrito;
	private Direccion direccionDeEntrega;
	private Sucursal sucursalElegida;
	private String idPago;
	private LocalDate fecha;
	private List<PedidoObserver> suscriptores = new ArrayList <>();
	
	public double precioAPagar() {
		return carrito.stream().mapToDouble(item->item.precioBaseCalculado()).sum();
		
	}
	
	public Pedido(Usuario usuario, EstadoDePedido estadoActual, List<Item> carrito, LocalDate fecha,
			List<PedidoObserver> suscriptores) {
		super();
		this.usuario = usuario;
		this.estadoActual = new Borrador(this);
		this.carrito = new ArrayList <>(); //admite items repetidos;
		this.fecha = fecha;
		this.suscriptores = suscriptores;
		AppEcommerce.getInstancia().agregarPedidoAHistorial(this);
	}

	/////////////////////ESTADO DE PEDIDO/////////////////////////////////////
	public void setEstadoDePedido(EstadoDePedido nuevoEstado) {
		EstadoDePedido anterior = this.estadoActual;
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
	
	public void incrementarStock() {
		carrito.stream().forEach(item->item.incrementarStock());
	}
	
	public void reembolsarCostoDeProductos(){
		usuario.agregarComprobante(new NotaDeCredito(getId(), precioAPagar()));
		
	}
	public void reembolsarCostoDeEnvio() {
		usuario.agregarComprobante(new NotaDeCredito(getId(), this.costoEnvio()));
	}
	public void validarQueElItemEstaEnElCarrito(Item item) {
		if(!this.getCarrito().contains(item)) {
			throw new RuntimeException ("No hay"+item.getNombre()+ "en el carrito");
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
		this.sucursalElegida = sucursal;
    } 
	
	public double getPesoTotal() {
		return  carrito.stream().mapToDouble(item->item.getPeso()).sum();
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
	
	public void setEnvio(Direccion direccion, Sucursal sucursal,  MetodoDeEnvio metodo) {
		this.setMetodoDeEnvio(metodo, sucursal);
		this.setDireccionDeEntrega(direccion);
	}

	public void setDireccionDeEntrega(Direccion direccionDeEntrega) {
		this.direccionDeEntrega = direccionDeEntrega;
	}

}
