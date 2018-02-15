package integracion.factoriaTransaccion;

import integracion.transaccion.Transaccion;
import integracion.transaccion.TransaccionMySQL;

public class FactoriaTransaccionImp extends FactoriaTransaccion {

	@Override
	public synchronized Transaccion nuevaTransaccion() {
		return new TransaccionMySQL();
	}

}
