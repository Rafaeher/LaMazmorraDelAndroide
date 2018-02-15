package presentacion.comando.producto;

import negocio.factoriaNegocio.FactoriaNegocio;
import negocio.producto.SAProducto;
import negocio.producto.TransferProducto;
import presentacion.comando.Comando;
import presentacion.controlador.Contexto;
import presentacion.eventos.Evento;

public class ComandoActualizarProducto implements Comando {

	@Override
	public Contexto execute(Object datos) {
		SAProducto saProducto = FactoriaNegocio.obtenerInstancia().generarSAProducto();

		switch (saProducto.actualizarProducto((TransferProducto) datos)) {

		case 0:
			return new Contexto(Evento.actualizarProducto, datos);
		
		default:
		case -1:
			return new Contexto(Evento.errorActualizarProducto, null);

		case -2:
			return new Contexto(Evento.errorArgumentos, null);
			
		case -3:
			return new Contexto(Evento.errorActualizarProductoInactivo, null);
			
		case -4:
			return new Contexto(Evento.errorActualizarProductoConMismoNombre, datos);
			
		case -9:
			return new Contexto(Evento.errorConexionBBDD, null);

		}
	}

}
