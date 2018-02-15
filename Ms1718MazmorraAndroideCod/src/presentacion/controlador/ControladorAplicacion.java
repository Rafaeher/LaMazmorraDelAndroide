package presentacion.controlador;

import presentacion.controlador.Contexto;

public abstract class ControladorAplicacion {
	private static ControladorAplicacion instancia;

	public static ControladorAplicacion obtenerInstancia() {
		if (instancia == null) {
			instancia = new ControladorAplicacionImp();
		}

		return instancia;
	}

	public abstract void accion(Contexto contexto);
}