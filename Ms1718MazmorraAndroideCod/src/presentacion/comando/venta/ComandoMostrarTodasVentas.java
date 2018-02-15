package presentacion.comando.venta;

import java.util.List;

import negocio.factoriaNegocio.FactoriaNegocio;
import negocio.venta.SAVenta;
import negocio.venta.TransferVenta;
import presentacion.comando.Comando;
import presentacion.controlador.Contexto;
import presentacion.eventos.Evento;

public class ComandoMostrarTodasVentas implements Comando {

	@Override
	public Contexto execute(Object datos) {
		SAVenta saVentas = FactoriaNegocio.obtenerInstancia().generarSAVenta();
		try {
			List<TransferVenta> ventas = saVentas.mostrarTodasVentas();
			if (ventas.isEmpty()) {
				return new Contexto(Evento.errorMostrarTodasVentas, null);
			} else {
				return new Contexto(Evento.mostrarTodasVentas, ventas);
			}
		} catch (Exception e) {
			return new Contexto(Evento.errorConexionBBDD, null);
		}

	}

}
