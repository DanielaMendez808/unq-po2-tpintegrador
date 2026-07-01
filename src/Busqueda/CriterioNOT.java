package Busqueda;

import catalogoEItems.Item;

public class CriterioNOT implements Criterio{
	public CriterioNOT(Criterio criterio) {
		super();
		this.criterio = criterio;
	}
	private Criterio criterio;
	public boolean cumple(Item item) {
		return !criterio.cumple(item); 
	}
	public Criterio getCriterio(){
		return this.criterio;
	}

}
