package presentacion.comando.producto;

import java.util.List;

import negocio.factoriaNegocio.FactoriaNegocio;
import negocio.producto.SAProducto;
import negocio.producto.TransferProducto;
import presentacion.comando.Comando;
import presentacion.controlador.Contexto;
import presentacion.eventos.Evento;

public class ComandoMostrarTodosProductos implements Comando {

	@Override
	public Contexto execute(Object datos) {
		SAProducto saProducto = FactoriaNegocio.obtenerInstancia().generarSAProducto();
		List<TransferProducto> productos;
		try {
			productos = saProducto.mostrarTodosProductos();
			if (productos.isEmpty()) {
				return new Contexto(Evento.errorMostrarTodosProductos, null);
			} else {
				return new Contexto(Evento.mostrarTodosProductos, productos);
			}
		} catch (Exception ex) {
			return new Contexto(Evento.errorConexionBBDD, null);
		}
	}

}
