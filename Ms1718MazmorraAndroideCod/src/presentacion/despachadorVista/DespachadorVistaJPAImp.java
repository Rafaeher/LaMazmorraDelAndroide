package presentacion.despachadorVista;

import presentacion.GUI;
import presentacion.controlador.Contexto;
import presentacion.factoriaPresentacion.FactoriaPresentacion;

public class DespachadorVistaJPAImp extends DespachadorVistaJPA {
	private GUI vista;
	
	@Override
	public void crearVista(Contexto contexto) {
		switch(contexto.getEvento()) {	
		
		case abrirMenuPrincipal:
			vista = FactoriaPresentacion.obtenerInstancia().crearGUI("menuPrincipal");
			vista.setVisible(true);
			break;
		
		case abrirMenuActividad:
			vista = FactoriaPresentacion.obtenerInstancia().crearGUI("menuActividad");
			vista.setVisible(true);
			break;
			
		case abrirMenuDepartamento:
			vista = FactoriaPresentacion.obtenerInstancia().crearGUI("menuDepartamento");
			vista.setVisible(true);
			break;
			
		case abrirMenuEmpleado:
			vista = FactoriaPresentacion.obtenerInstancia().crearGUI("menuEmpleado");
			vista.setVisible(true);
			break;
			
		default:
			vista.actualizar(contexto);
			break;
			
		}

	}

}
