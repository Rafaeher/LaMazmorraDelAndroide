package presentacion.comando.venta;

import presentacion.comando.Comando;
import presentacion.controlador.Contexto;

import java.util.Iterator;

import negocio.factoriaNegocio.FactoriaNegocio;
import negocio.venta.LineaVenta;
import negocio.venta.SAVenta;
import negocio.venta.TransferVenta;
import presentacion.eventos.Evento;

public class ComandoDevolucionProducto implements Comando {

	@Override
	public Contexto execute(Object datos) {
		SAVenta saVenta = FactoriaNegocio.obtenerInstancia().generarSAVenta();
		int idProducto = 0, cantidadADevolver = 0;
		
		Iterator<LineaVenta> itProducto = ((TransferVenta) datos).getProductos().values().iterator();
		
		while(itProducto.hasNext()) {
			LineaVenta lineaVenta = itProducto.next();
			idProducto = lineaVenta.getId();
			cantidadADevolver = lineaVenta.getCantidad();
		}
		
		switch (saVenta.devolverProducto((TransferVenta) datos, idProducto, cantidadADevolver)) {

		case 0:
			return new Contexto(Evento.devolverProducto, (TransferVenta) datos);

		default:
		case -1:
			return new Contexto(Evento.errorDevolverProducto, idProducto);

		case -2:
			return new Contexto(Evento.errorArgumentos, null);
			
		case -3:
			return new Contexto(Evento.errorDevolverProductoVentaInexistente, null);
			
		case -4:
			return new Contexto(Evento.errorDevolverProductoInexistente, idProducto);
			
		case -5:
			return new Contexto(Evento.errorDevolverProductoCantidadExcesiva, cantidadADevolver);

		case -6:
			return new Contexto(Evento.errorDevolverProductoClienteInactivo, null);
			
		case -7:
			return new Contexto(Evento.errorDevolverProductoReactivacionProducto, null);
			
		case -9:
			return new Contexto(Evento.errorConexionBBDD, null);
			
		case -12:
			return new Contexto(Evento.errorDevolverProductoCantidadExcesivaYReactivacion, cantidadADevolver);
		}
	}
}
