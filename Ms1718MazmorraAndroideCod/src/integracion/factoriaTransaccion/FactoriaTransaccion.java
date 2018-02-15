package integracion.factoriaTransaccion;

import integracion.transaccion.Transaccion;

public abstract class FactoriaTransaccion {
	private static FactoriaTransaccion instancia;
	
	public synchronized static FactoriaTransaccion obtenerInstancia() {
		if (instancia == null) {
			instancia = new FactoriaTransaccionImp();
		}
		
		return instancia;
	}
	
	public abstract Transaccion nuevaTransaccion();
}
