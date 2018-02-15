package presentacion.despachadorVista;

import presentacion.controlador.Contexto;

public abstract class DespachadorVista {	
	private static DespachadorVista instancia;

	public static DespachadorVista obtenerInstancia() {
		if (instancia == null)
			instancia = new DespachadorVistaImp();
		return instancia;
	}

	public abstract void crearVista(Contexto contexto);

}
