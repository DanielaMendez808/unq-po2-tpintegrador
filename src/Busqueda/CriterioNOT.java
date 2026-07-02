package Busqueda;

import catalogoEItems.Item;

public class CriterioNOT implements Criterio{
	private Criterio criterio;
	public CriterioNOT(Criterio criterio) {
		super();
		this.criterio = criterio;
	}
	public boolean cumple(Item item) {
		return !criterio.cumple(item); 
	}
	public Criterio getCriterio(){
		return this.criterio;
	}

}
