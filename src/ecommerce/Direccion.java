package ecommerce;

public class Direccion {

    private String calle;
    private String ciudad;
    private String provincia;
    private String codigoPostal;

    public Direccion(String calle, String ciudad, String provincia, String codigoPostal) {
        this.calle = calle;
        this.ciudad = ciudad;
        this.codigoPostal = codigoPostal;
        this.provincia = provincia;
    }

}
