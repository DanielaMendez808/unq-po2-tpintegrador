package Busqueda;

import ecommerce.Item;

public interface Criterio {
	boolean cumple(Item item);
}
