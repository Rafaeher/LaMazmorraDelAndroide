package presentacion.comando;

import presentacion.controlador.Contexto;

public interface Comando {
	Contexto execute(Object datos);
}
