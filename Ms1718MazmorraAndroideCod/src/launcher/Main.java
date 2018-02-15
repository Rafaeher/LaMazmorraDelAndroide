package launcher;

import presentacion.factoriaPresentacion.FactoriaPresentacion;

public class Main {
	public static void main(String[] args) {
		FactoriaPresentacion.obtenerInstancia().crearGUI("menuPrincipal").setVisible(true);
	}
}
