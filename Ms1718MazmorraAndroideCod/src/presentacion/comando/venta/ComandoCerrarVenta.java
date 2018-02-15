package presentacion.comando.venta;

import negocio.factoriaNegocio.FactoriaNegocio;
import negocio.venta.SAVenta;
import negocio.venta.TransferVenta;
import presentacion.comando.Comando;
import presentacion.controlador.Contexto;
import presentacion.eventos.Evento;

public class ComandoCerrarVenta implements Comando {

	@Override
	public Contexto execute(Object datos) {
		SAVenta saVenta = FactoriaNegocio.obtenerInstancia().generarSAVenta();
		
		int respuesta = saVenta.cerrarVenta((TransferVenta) datos);
		
		switch (respuesta) {
		case 0: 
			return new Contexto(Evento.cerrarVenta, datos);
		case -3:
			return new Contexto(Evento.errorCerrarVentaCliente, datos);
			
		case -5:
			return new Contexto(Evento.errorCerrarVentaCarrito, datos);
			
		case -9:
			return new Contexto(Evento.errorConexionBBDD, null);
		case -1:
		case -2:
		case -4:
		default:
			return new Contexto(Evento.errorCerrarVenta, datos);
				
		}
	}

}
