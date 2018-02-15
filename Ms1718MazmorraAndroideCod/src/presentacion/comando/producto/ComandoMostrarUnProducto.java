package presentacion.comando.producto;

import negocio.factoriaNegocio.FactoriaNegocio;
import negocio.producto.SAProducto;
import negocio.producto.TransferProducto;
import presentacion.comando.Comando;
import presentacion.controlador.Contexto;
import presentacion.eventos.Evento;

public class ComandoMostrarUnProducto implements Comando {

	@Override
	public Contexto execute(Object datos) {
		SAProducto saProducto = FactoriaNegocio.obtenerInstancia().generarSAProducto();
		TransferProducto tProRead;
		try {
			if (null == (tProRead = saProducto.mostrarUnProducto((TransferProducto) datos))) {
				return new Contexto(Evento.errorMostrarUnProducto, null);
			} else {
				return new Contexto(Evento.mostrarUnProducto, tProRead);
			}
		} catch (Exception ex) {
			return new Contexto(Evento.errorConexionBBDD, null);
		}
	}

}
