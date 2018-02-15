package integracion.transaccion;

public interface Transaccion {
	
	/*
	 * Inicia una transacción
	 */
	void iniciar() throws Exception;
	
	/*
	 * Compromete todos los cambios realizados por una transacción.
	 */
	void comprometer();
	
	/*
	 * Deshace todos los cambios realizados por una transaccion
	 */
	void deshacer();
	
	/*
	 * Devuelve el recurso que permite conectarse al sistema transaccional
	 */
	Object getResource();
}
