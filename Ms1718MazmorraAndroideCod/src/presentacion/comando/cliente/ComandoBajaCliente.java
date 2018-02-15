package presentacion.comando.cliente;

import negocio.cliente.SACliente;
import negocio.cliente.TransferCliente;
import negocio.factoriaNegocio.FactoriaNegocio;
import presentacion.comando.Comando;
import presentacion.controlador.Contexto;
import presentacion.eventos.Evento;

public class ComandoBajaCliente implements Comando {

	@Override
	public Contexto execute(Object datos) {
		SACliente saCliente = FactoriaNegocio.obtenerInstancia().generarSACliente();

		switch (saCliente.bajaCliente((TransferCliente) datos)) {

		case 0:
			return new Contexto(Evento.bajaCliente, datos);
			
		default:
		case -1:
			return new Contexto(Evento.errorBajaCliente, datos);

		case -2:
			return new Contexto(Evento.errorArgumentos, null);

		case -3:
			return new Contexto(Evento.errorBajaClienteDuplicado, null);
			
		case -9:
			return new Contexto(Evento.errorConexionBBDD, null);
			
		}
	}

}
