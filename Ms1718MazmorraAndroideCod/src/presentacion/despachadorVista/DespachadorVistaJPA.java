package presentacion.despachadorVista;

import presentacion.controlador.Contexto;

public abstract class DespachadorVistaJPA {
	private static DespachadorVistaJPA instancia;
	
	public static DespachadorVistaJPA obtenerInstancia() {
		if (instancia == null) {
			instancia = new DespachadorVistaJPAImp();
		}
		
		return instancia;
	}
	
	public abstract void crearVista(Contexto contexto);
}
