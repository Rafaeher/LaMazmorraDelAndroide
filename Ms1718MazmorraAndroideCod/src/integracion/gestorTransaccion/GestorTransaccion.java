package integracion.gestorTransaccion;

import integracion.transaccion.Transaccion;

public abstract class GestorTransaccion {
	private static GestorTransaccion instancia;
	
	public synchronized static GestorTransaccion obtenerInstancia() {
		if (instancia == null) {
			instancia = new GestorTransaccionImp();
		}
		
		return instancia;
	}
	
	public abstract Transaccion nuevaTransaccion();
	public abstract void eliminarTransaccion();
	public abstract Transaccion obtenerTransaccion(); 
}
