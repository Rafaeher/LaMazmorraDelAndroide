package presentacion.factoriaPresentacion;

import presentacion.GUI;


public abstract class FactoriaPresentacion {
	private static FactoriaPresentacion instancia;

	public synchronized static FactoriaPresentacion obtenerInstancia() {
		if (instancia == null) {
			instancia = new FactoriaPresentacionImpCargaDinamica();
		}
		
		return instancia;
	}

	public abstract GUI crearGUI(String nombreGUI);
}
