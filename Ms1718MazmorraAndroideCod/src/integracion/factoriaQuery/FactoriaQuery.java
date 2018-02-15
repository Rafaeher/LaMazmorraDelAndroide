package integracion.factoriaQuery;

import integracion.query.Query;

public abstract class FactoriaQuery {
	private static FactoriaQuery instancia;
	
	public synchronized static FactoriaQuery obtenerInstancia() {
		if (instancia == null) {
			instancia = new FactoriaQueryImpCargaDinamica();
		}
		
		return instancia;
	}
	
	public abstract Query nuevaQuery(String tipoQuery);
}
