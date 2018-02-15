package presentacion.controlador;

import presentacion.comando.Comando;
import presentacion.despachadorVista.DespachadorVista;
import presentacion.factoriaComandos.FactoriaComandos;

public class ControladorAplicacionImp extends ControladorAplicacion {

	@Override
	public void accion(Contexto contexto) {
		Comando comando = FactoriaComandos.obtenerInstancia().obtenerComando(contexto.getEvento());
		contexto = comando.execute(contexto.getDatos());
		DespachadorVista.obtenerInstancia().crearVista(contexto);	
	}
}
		