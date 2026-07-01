package busqueda;

import java.util.ArrayList;
import java.util.Arrays;

import Busqueda.Criterio;
import catalogoEItems.Catalogo;
import catalogoEItems.Producto;

public class TestMotorDeBusqueda {
	private Catalogo catalogo;
	private Criterio criterioDeBusqueda;
	private Producto cableHDMI;
	private Producto cargadorMotorola;
	
	@BeforeEach
	public setUp() {
		cableHDMI = new Producto()
		catalogo= new Catalogo("catalogo2026",new ArrayList<>(Arrays.asList(cableHDMI,cargadorMotorola));
	}
}
