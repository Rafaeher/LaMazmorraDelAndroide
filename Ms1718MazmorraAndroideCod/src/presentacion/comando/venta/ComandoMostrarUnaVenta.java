package presentacion.comando.venta;

import negocio.factoriaNegocio.FactoriaNegocio;
import negocio.venta.SAVenta;
import negocio.venta.TransferVenta;
import presentacion.comando.Comando;
import presentacion.controlador.Contexto;
import presentacion.eventos.Evento;

public class ComandoMostrarUnaVenta implements Comando {

	@Override
	public Contexto execute(Object datos) {
		SAVenta saVenta = FactoriaNegocio.obtenerInstancia().generarSAVenta();
		try {
			TransferVenta venta = saVenta.mostrarUnaVenta((TransferVenta) datos);
			
			if (venta == null) {
				return new Contexto(Evento.errorMostrarUnaVenta, datos); 
			} else {
				return new Contexto(Evento.mostrarUnaVenta, venta);
			}
		} catch (Exception ex){
			return new Contexto(Evento.errorConexionBBDD, null);
		}
		
	}

}
