package presentacion.comando.producto;

import negocio.factoriaNegocio.FactoriaNegocio;
import negocio.producto.SAProducto;
import negocio.producto.TransferProducto;
import presentacion.comando.Comando;
import presentacion.controlador.Contexto;
import presentacion.eventos.Evento;

public class ComandoAltaProducto implements Comando {

	@Override
	public Contexto execute(Object datos) {
		SAProducto saProducto = FactoriaNegocio.obtenerInstancia().generarSAProducto();

		switch (saProducto.altaProducto((TransferProducto) datos)) {

		case 0:
			return new Contexto(Evento.altaProducto, datos);
			
		default:
		case -1:
			return new Contexto(Evento.errorAltaProductoDuplicadoActivo, datos);

		case -2:
			return new Contexto(Evento.errorArgumentos, null);
		
		case -3:
			return new Contexto(Evento.errorAltaProductoDuplicadoInactivo, null);
			
		case -9:
			return new Contexto(Evento.errorConexionBBDD, null);
		}
		

	}

}
