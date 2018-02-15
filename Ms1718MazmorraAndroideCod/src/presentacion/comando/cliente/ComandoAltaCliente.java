package presentacion.comando.cliente;

import negocio.cliente.SACliente;
import negocio.cliente.TransferCliente;
import negocio.factoriaNegocio.FactoriaNegocio;
import presentacion.comando.Comando;
import presentacion.controlador.Contexto;
import presentacion.eventos.Evento;

public class ComandoAltaCliente implements Comando {

	@Override
	public Contexto execute(Object datos) {
		
		SACliente saCliente = FactoriaNegocio.obtenerInstancia().generarSACliente();

		switch (saCliente.altaCliente((TransferCliente) datos)) {

		case 0:
			return new Contexto(Evento.altaCliente, datos);

		case -1:
			return new Contexto(Evento.errorAltaCliente, datos);
			
		case -2:
			return new Contexto(Evento.errorCambioTipoCliente, null);
			
		case -3:
			return new Contexto(Evento.errorAltaClienteDuplicado, null);
		
		case -4:
			return new Contexto(Evento.errorAltaClienteDistintoDescuento, datos);
			
		case -9:
			return new Contexto(Evento.errorConexionBBDD, null);
			
		default:
			return null;
		}
		
	}

}
