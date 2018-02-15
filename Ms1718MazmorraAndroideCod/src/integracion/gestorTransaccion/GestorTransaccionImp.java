package integracion.gestorTransaccion;

import java.util.concurrent.ConcurrentHashMap;

import integracion.factoriaTransaccion.FactoriaTransaccion;
import integracion.transaccion.Transaccion;

public class GestorTransaccionImp extends GestorTransaccion {
	private ConcurrentHashMap<Thread, Transaccion> transacciones;
	
	public GestorTransaccionImp() {
		this.transacciones = new ConcurrentHashMap<>();
	}
	
	@Override
	public Transaccion nuevaTransaccion() {
		Thread currentThread = Thread.currentThread();
		Transaccion transaccion = this.transacciones.get(currentThread);
		
		if (transaccion == null) {
			transaccion = FactoriaTransaccion.obtenerInstancia().nuevaTransaccion();
			this.transacciones.put(currentThread, transaccion);
		}
		
		return transaccion;
	}

	@Override
	public void eliminarTransaccion() {
		this.transacciones.remove(Thread.currentThread());
	}

	@Override
	public Transaccion obtenerTransaccion() {
		return this.transacciones.get(Thread.currentThread());
	}

}
