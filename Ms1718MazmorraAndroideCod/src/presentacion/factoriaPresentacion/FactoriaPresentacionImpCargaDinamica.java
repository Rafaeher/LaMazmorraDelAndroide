package presentacion.factoriaPresentacion;

import java.io.FileReader;
import java.util.Properties;

import presentacion.GUI;


public class FactoriaPresentacionImpCargaDinamica extends FactoriaPresentacion {
	
	@Override
	public GUI crearGUI(String nombreGUI) {
		GUI gui = null;
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		String className = null;
		
		try {
			Properties properties = new Properties();
			properties.load(new FileReader("datos/guis.properties"));
			className = properties.getProperty(nombreGUI);
			
			Class t = loader.loadClass(className);
			Class.forName(className);
			gui =  (GUI) t.newInstance();
			
		} catch (Exception e) {
			System.out.println("Error en la carga dinámica de las GUIs " + e.getMessage());
		}

		return gui;	
	}
}
