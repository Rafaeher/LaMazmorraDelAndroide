package presentacion.comando.cliente;

import negocio.cliente.SACliente;
import negocio.cliente.TransferCliente;
import negocio.factoriaNegocio.FactoriaNegocio;
import presentacion.comando.Comando;
import presentacion.controlador.Contexto;
import presentacion.eventos.Evento;

public class ComandoActualizarCliente implements Comando {

	@Override
	public Contexto execute(Object datos) {
		SACliente saCliente = FactoriaNegocio.obtenerInstancia().generarSACliente();
		
		switch (saCliente.actualizarCliente((TransferCliente) datos)) {

		case 0:
			return new Contexto(Evento.actualizarCliente, datos);

		case -1:
			return new Contexto(Evento.errorActualizarCliente, null);

		case -2:
			return new Contexto(Evento.errorActualizarClienteConMismoEmail, datos);
			
		case -3:
			return new Contexto(Evento.errorActualizarClienteReactivacion, datos);
			
		case -4:
			return new Contexto(Evento.errorActualizarClienteTiposNoCoinciden, datos);
			
		case -9:
			return new Contexto(Evento.errorConexionBBDD, null);
			
			default:
				return null;

		}
		
	
	}

}
