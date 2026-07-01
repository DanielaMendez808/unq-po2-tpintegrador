package Busqueda;

import catalogoEItems.Item;

public class CriterioAND implements Criterio {
	private Criterio criterio1;
	private Criterio criterio2;
	public boolean cumple(Item item) {
		return criterio1.cumple(item) && criterio2.cumple(item);
	}
	public Criterio getCriterio1(){
		return this.criterio1;
	}
	public Criterio getCriterio2(){
		return this.criterio2;
	}
	public CriterioAND(Criterio criterio1, Criterio criterio2) {
		super();
		this.criterio1 = criterio1;
		this.criterio2 = criterio2;
	}
}
