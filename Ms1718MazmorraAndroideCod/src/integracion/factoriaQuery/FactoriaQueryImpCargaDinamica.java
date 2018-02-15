package integracion.factoriaQuery;

import java.io.FileReader;
import java.util.Properties;

import integracion.query.Query;

public class FactoriaQueryImpCargaDinamica extends FactoriaQuery {

	@Override
	public Query nuevaQuery(String tipoQuery) {
		Query query = null;
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		String className = null;
		
		try {
			Properties properties = new Properties();
			properties.load(new FileReader("datos/queries.properties"));
			className = properties.getProperty(tipoQuery);
			
			Class<?> t = loader.loadClass(className);
			Class.forName(className);
			query =  (Query) t.newInstance();
			
		} catch (Exception e) {
			System.out.println("Error en la carga dinámica de las queries " + e.getMessage());
		}

		return query;		
	}
}
