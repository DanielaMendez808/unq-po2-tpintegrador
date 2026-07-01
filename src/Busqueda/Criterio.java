package Busqueda;

import catalogoEItems.Item;

public interface Criterio {
	boolean cumple(Item item);
}
