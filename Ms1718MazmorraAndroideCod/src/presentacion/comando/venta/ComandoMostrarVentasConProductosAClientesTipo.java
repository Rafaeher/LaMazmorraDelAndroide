package presentacion.comando.venta;

import java.util.List;

import negocio.factoriaNegocio.FactoriaNegocio;
import negocio.venta.SAVenta;
import negocio.venta.TransferVenta;
import presentacion.comando.Comando;
import presentacion.controlador.Contexto;
import presentacion.eventos.Evento;
import integracion.query.DatosQuery;


public class ComandoMostrarVentasConProductosAClientesTipo implements Comando {

	@Override
	public Contexto execute(Object datos) {
		SAVenta saVenta = FactoriaNegocio.obtenerInstancia().generarSAVenta();
		try {
		List<TransferVenta> ventas = saVenta.mostrarVentasConProductosAClientesTipo((DatosQuery) datos);
		
		
		if (ventas == null) {
			return new Contexto(Evento.errorMostrarVentasConProductosAClientesTipoProductoNoExistente, datos);
		} else if (ventas.isEmpty()) {
			return new Contexto(Evento.errorMostrarVentasConProductosAClientesTipo, datos);
		} else {
			return new Contexto(Evento.mostrarVentasConProductosAClientesTipo, ventas);
		}
		} catch (Exception e) {
			return new Contexto(Evento.errorConexionBBDD, null);
		}
	}

}
