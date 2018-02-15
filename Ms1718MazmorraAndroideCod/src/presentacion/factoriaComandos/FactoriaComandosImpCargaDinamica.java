package presentacion.factoriaComandos;


import java.io.FileReader;
import java.util.Properties;

import presentacion.comando.Comando;
import presentacion.eventos.Evento;

public class FactoriaComandosImpCargaDinamica extends FactoriaComandos {

	@Override
	public Comando obtenerComando(Evento evento) {
		Comando comando = null;
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		String className = null;
		
		try {
			Properties properties = new Properties();
			properties.load(new FileReader("datos/comandos.properties"));
			className = properties.getProperty(evento.toString());
			
			Class t = loader.loadClass(className);
			Class.forName(className);
			comando =  (Comando) t.newInstance();
			
		} catch (Exception e) {
			System.out.println("Error en la carga dinámica de los comandos " + e.getMessage());
		}

		return comando;	
	}
}
