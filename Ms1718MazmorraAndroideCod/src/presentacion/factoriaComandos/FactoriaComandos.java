package presentacion.factoriaComandos;

import presentacion.comando.Comando;
import presentacion.eventos.Evento;

public abstract class FactoriaComandos {
	private static FactoriaComandos instancia;
	
	public static FactoriaComandos obtenerInstancia() {
		if (instancia == null)
			instancia = new FactoriaComandosImpCargaDinamica();
		return instancia;
	}

	public abstract Comando obtenerComando(Evento evento);
}
